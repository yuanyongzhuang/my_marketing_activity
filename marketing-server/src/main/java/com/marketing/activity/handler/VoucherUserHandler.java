package com.marketing.activity.handler;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.enums.UserVoucherStatusEnum;
import com.marketing.activity.mapper.VoucherUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
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
