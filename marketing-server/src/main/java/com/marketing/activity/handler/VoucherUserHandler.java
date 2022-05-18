package com.marketing.activity.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.PhoneUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.dto.PackageInfoByPackageIdDTO;
import com.marketing.activity.domain.dto.UserInfoDTO;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.domain.param.VoucherReceiveDataPageParam;
import com.marketing.activity.domain.resp.VoucherReceiveDataResp;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.enums.UserVoucherStatusEnum;
import com.marketing.activity.helper.VoucherUserHelper;
import com.marketing.activity.mapper.mysql.marketingActivity.VoucherUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户券处理器
 * </p>
 *
 * @author yyz
 * @since 2022/4/18
 */
@Slf4j
@Component
public class VoucherUserHandler {

    @Resource
    VoucherUserMapper voucherUserMapper;

    @Resource
    VoucherUserHelper voucherUserHelper;

    @Resource
    UserHandler userHandler;

    /**
     * 过滤券
     * @param voucherUserList 券
     * @return obj
     */
    public List<VoucherUser> filterAvailableVoucher(List<VoucherUser> voucherUserList) {
        //filter used voucher
        List<VoucherUser> filterCanUseList = voucherUserList.stream()
                .filter(info -> !UserVoucherStatusEnum.USED.getValue().equals(info.getUseStatus())).collect(Collectors.toList());
        //available
        List<VoucherUser> availableList = Lists.newArrayList();
        //expire
        List<VoucherUser> expireList = Lists.newArrayList();
         for(VoucherUser info: filterCanUseList){
             if(info.getExpireTime().before(BaseContextHandler.getAccessTime())){
                 //expire
                 expireList.add(info);
             }else{
                 availableList.add(info);
             }
         }

         if(CollUtil.isNotEmpty(expireList)){
             //异步处理过期券
             this.asyncExpire(expireList);
         }

        return availableList;
    }

    /**
     * 异步处理过期券
     * @param expireList 过期券集合
     */
    @Async
    public void asyncExpire(List<VoucherUser> expireList) {
        for(VoucherUser voucherUser: expireList){
            VoucherUser updateInfo = new VoucherUser();
            updateInfo.setId(voucherUser.getId());
            updateInfo.setUseStatus(UserVoucherStatusEnum.EXPIRED.getValue());
            voucherUserMapper.updateById(updateInfo);

            log.info("asyncExpire userId={}, voucherUserId={}, code={}", voucherUser.getUserId(), voucherUser.getVoucherId(), voucherUser.getVoucherCode());
        }
    }

    /**
     * 过滤券的指定商品
     * @param useRangeType 使用商品范围 0全类品 1指定商品 2指定品类
     * @param expandJson 券使用范围
     * @param productList 指定商品集
     * @return
     */
    public List<PackageInfoByPackageIdDTO> filterProductList(Integer useRangeType, String expandJson, List<PackageInfoByPackageIdDTO> productList) {
        List<Integer> ids = Lists.newArrayList();
        if(useRangeType > 0){
            JSONObject jsonObject = JSONObject.parseObject(expandJson);
            JSONArray useRangeContent = jsonObject.getJSONArray("useRangeContent");
            List<Integer> integers = useRangeContent.toJavaList(Integer.class);
            ids.addAll(integers);
        }
        switch (useRangeType){
            case 0:
                return  productList;
            case 1:
                return productList.stream().filter(pInfo -> ids.contains(pInfo.getPackageId())).collect(Collectors.toList());
            case 2:
                return productList.stream().filter(pInfo -> ids.contains(pInfo.getExamId())).collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }

    public String availableValidate(VoucherUser voucherUser) {
        if(voucherUser == null || EnabledStatusEnum.YES.getValue().equals(voucherUser.getDeleteStatus())){
            return ErrorMsg.USER_COUPON_IS_NULL;
        }
        //使用状态 0未使用 1已使用 2已过期
        Integer useStatus = voucherUser.getUseStatus();
        if(UserVoucherStatusEnum.USED.getValue().equals(useStatus)){
            return ErrorMsg.USER_COUPON_IS_USED;
        }
        if(UserVoucherStatusEnum.EXPIRED.getValue().equals(useStatus) || voucherUser.getExpireTime().before(BaseContextHandler.getAccessTime())){
            return ErrorMsg.COUPON_IS_EXPIRED;
        }
        return null;
    }

    public Map<Long, List<VoucherUser>> getUserAllVoucherMap(Long userId) {
        if(userId == null || userId <= 0){
            return Maps.newHashMap();
        }
        List<VoucherUser> list = voucherUserHelper.getAllList(userId);
        if(CollectionUtil.isEmpty(list)){
            return Maps.newHashMap();
        }
        return list.stream().collect(Collectors.groupingBy(VoucherUser::getVoucherId));
    }

    private List<VoucherUser> getUserVoucherList(Long userId, Long voucherId, String code){
        LambdaQueryWrapper<VoucherUser> queryWrapper = Wrappers.lambdaQuery(VoucherUser.class);
        queryWrapper.eq(VoucherUser::getDeleteStatus, EnabledStatusEnum.NO.getValue());
        queryWrapper.eq(VoucherUser::getUserId, userId);
        if(voucherId != null){
            queryWrapper.eq(VoucherUser::getUserId, voucherId);
        }
        if(StringUtils.isNotEmpty(code)){
            queryWrapper.eq(VoucherUser::getVoucherCode, code);
        }

        return voucherUserMapper.selectList(queryWrapper);
    }

    public List<VoucherUser> getUserVoucherList(Long userId, Long voucherId) {
        return this.getUserVoucherList(userId, voucherId, null);
    }

    /**
     *
     * @param pageParam
     * @return
     */
    public List<VoucherReceiveDataResp> voucherReceiveData(VoucherReceiveDataPageParam pageParam) {

        Assert.notNull(pageParam, ErrorMsg.PARAM_IS_NULL);

        if(StringUtils.isNotEmpty(pageParam.getMobile())){
            UserInfoDTO userInfoDTO = userHandler.getUserInfoByMobile(pageParam.getMobile());
            Assert.notNull(userInfoDTO, ErrorMsg.USER_IS_NOT_EXIST);
            pageParam.setUserId(userInfoDTO.getGroupId().longValue());
        }

        List<VoucherReceiveDataResp> respList = new ArrayList<>();

        List<VoucherUser> receiveDataList = voucherUserMapper.getReceiveDataPageList(pageParam);
        if(CollUtil.isEmpty(receiveDataList)){
            return null;
        }

        Set<Integer> userIds = receiveDataList.stream().map(info -> info.getUserId().intValue()).collect(Collectors.toSet());
        Map<Integer, UserInfoDTO> userMap = userHandler.getUserMapByUserIds(userIds);

        for(VoucherUser voucherUser: receiveDataList){
            VoucherReceiveDataResp resp = new VoucherReceiveDataResp();
            resp.setUserVoucherId(voucherUser.getId().toString());
            resp.setReceiveTime(DateUtil.formatDateTime(voucherUser.getCreateTime()));
            resp.setUsedTime(voucherUser.getUseTime() == null ? "":DateUtil.formatDateTime(voucherUser.getUseTime()));
            resp.setExpireTime(DateUtil.formatDateTime(voucherUser.getExpireTime()));

            Integer useStatus = voucherUser.getUseStatus();
            if(voucherUser.getExpireTime().before(BaseContextHandler.getAccessTime())){
                useStatus = 2;
            }
            resp.setUseStatus(useStatus);
            resp.setUseStatusStr(this.getuserStatusStr(useStatus));
            resp.setUserId(voucherUser.getUserId().toString());
            UserInfoDTO userInfoDTO = userMap.get(voucherUser.getUserId().intValue());
            resp.setMobile(userInfoDTO == null ? "-":userInfoDTO.getMobile());
            resp.setHideMobile(userInfoDTO == null ? "-":PhoneUtil.hideBetween(userInfoDTO.getMobile()).toString());

            respList.add(resp);
        }

        return respList;
    }

    private String getuserStatusStr(Integer useStatus) {
        switch (useStatus) {
            case 0:
                return "未使用";
            case 1:
                return "已使用";
            case 2:
                return "已过期";
            default:
                return "-";
        }
    }

    //    /**
//     * 过滤券.
//     *
//     * @param userVoucherList 券
//     * @return pair
//     */
//    public Pair<List<VoucherUser>/* 可用 */, /* 已过期 */List<VoucherUser>> filterVoucher(List<VoucherUser> userVoucherList) {
//
//        // filter used voucher
//        List<VoucherUser> filterCanUseList = userVoucherList.stream().filter(info -> info.getUseStatus() == 0).collect(Collectors.toList());
//
//        Date accessTime = BaseContextHandler.getAccessTime() == null ? new Date() : BaseContextHandler.getAccessTime();
//
//        // available
//        List<VoucherUser> availableList = Lists.newArrayList();
//        // expire
//        List<VoucherUser> expireList = Lists.newArrayList();
//
//        for (VoucherUser info : filterCanUseList) {
//            if (info.getExpireTime().before(accessTime)) {
//                // expire
//                expireList.add(info);
//            } else {
//                // available
//                availableList.add(info);
//            }
//        }
//
//        return new Pair<>(availableList, expireList);
//    }
}
