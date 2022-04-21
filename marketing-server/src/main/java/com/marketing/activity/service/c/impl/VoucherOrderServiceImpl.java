package com.marketing.activity.service.c.impl;

import cn.hutool.core.lang.Assert;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.dto.PackageInfoByPackageIdDTO;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.domain.param.OrderConfirmCheckVoucherParam;
import com.marketing.activity.domain.resp.OrderConfirmCheckVoucherResp;
import com.marketing.activity.handler.ProductHandler;
import com.marketing.activity.handler.VoucherUserHandler;
import com.marketing.activity.mapper.VoucherUserMapper;
import com.marketing.activity.service.b.VoucherInfoService;
import com.marketing.activity.service.c.VoucherOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 订单优惠券服务
 * </p>
 *
 * @author yyz
 * @since 2022/4/21
 */
@Slf4j
@Service
public class VoucherOrderServiceImpl implements VoucherOrderService {

    @Resource
    ProductHandler productHandler;

    @Resource
    VoucherInfoService voucherInfoService;

    @Resource
    VoucherUserMapper voucherUserMapper;

    @Resource
    VoucherUserHandler voucherUserHandler;

    @Override
    public CommonResult<OrderConfirmCheckVoucherResp> confirm(OrderConfirmCheckVoucherParam checkParam) {
        Assert.notNull(checkParam, ErrorMsg.PARAM_IS_NULL);

        Long userVoucherId = checkParam.getUserVoucherId();
        Set<Integer> productIds = checkParam.getProductIds();

        Assert.isFalse((userVoucherId == null || userVoucherId <= 0), ErrorMsg.VOUCHER_ID_IS_NULL);
        Assert.notEmpty(productIds, ErrorMsg.PRODUCT_ID_IS_NULL);

        //用户券
        VoucherUser voucherUserInfo = voucherUserMapper.selectById(userVoucherId);
        //使用、过期校验
        String availableValidateResult = voucherUserHandler.availableValidate(voucherUserInfo);
        Assert.isNull(availableValidateResult, availableValidateResult);
        //券
        VoucherInfo voucherInfo = voucherInfoService.getById(voucherUserInfo.getVoucherId());
        Assert.notNull(voucherInfo, ErrorMsg.VOUCHER_NOT_EXIST);
        //订单二次确认
        List<PackageInfoByPackageIdDTO> productList = productHandler.batchGetProductListByIds(productIds);
        Assert.notEmpty(productList,"商品信息不存在");
        //使用商品范围 0全品类 1指定商品 2指定品类
        Integer useRangeType = voucherInfo.getUseRangeType();
        List<PackageInfoByPackageIdDTO> filterProductList = voucherUserHandler.filterProductList(useRangeType, voucherInfo.getExpandJson(), productList);
        Assert.notEmpty(filterProductList, "商品不满足券使用范围");
        //满额（门槛价）
        BigDecimal fullAmounts = voucherInfo.getFullAmounts() == null ? BigDecimal.ZERO : voucherInfo.getFullAmounts();
        if(fullAmounts.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal sum = filterProductList.stream().map(PackageInfoByPackageIdDTO::getPackagePrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            Assert.isFalse(sum.compareTo(fullAmounts) < 0, String.format("商品不满足使用门槛[满%s元可用]", fullAmounts.stripTrailingZeros().toPlainString()));
        }

        //组装返回参数
        OrderConfirmCheckVoucherResp respInfo = new OrderConfirmCheckVoucherResp();
        respInfo.setUserVoucherId(voucherUserInfo.getId().toString());
        respInfo.setVoucherId(voucherInfo.getId().toString());
        respInfo.setVoucherCode(voucherInfo.getInnerCode());
        respInfo.setVoucherName(voucherInfo.getShowName());
        respInfo.setFullAmounts(voucherInfo.getFullAmounts());
        respInfo.setDiscountType(voucherInfo.getDiscountType());
        respInfo.setDiscountAmounts(voucherInfo.getDiscountAmounts());

        return CommonResult.success(respInfo);
    }
}
