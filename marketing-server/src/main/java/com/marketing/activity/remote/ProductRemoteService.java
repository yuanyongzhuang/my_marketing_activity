package com.marketing.activity.remote;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.marketing.activity.base.ApiErrorCode;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.SignKeyConstant;
import com.marketing.activity.domain.dto.PackageInfoByPackageIdDTO;
import com.marketing.activity.domain.vo.PackageInfoByPackageIdVO;
import com.marketing.activity.remote.api.ProductFeignApi;
import com.marketing.activity.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * 商品
 * @author yyz
 */
@Slf4j
@Service
public class ProductRemoteService {

    @Resource
    ProductFeignApi productFeignApi;

    public List<PackageInfoByPackageIdDTO> getProductListByIds(Set<Integer> productIds){
        PackageInfoByPackageIdVO queryParam = PackageInfoByPackageIdVO.builder()
                .packageIdList(Lists.newArrayList(productIds))
                .build();
        LinkedHashMap<String, String> linkedHashMap = SignUtil.generateSignParam(JSONObject.toJSONString(queryParam), SignKeyConstant.OPEN_API_PUBLIC_PRODUCT);
        log.info("getProductListByIds 请求参数 linkedHashMap={}", JSONObject.toJSONString(linkedHashMap));
        CommonResult<List<PackageInfoByPackageIdDTO>> infoByPackageIdList = productFeignApi.getInfoByPackageIdList(linkedHashMap);
        log.info("getProductListByIds 请求参数 infoByPackageIdList={}", JSONObject.toJSONString(infoByPackageIdList));
        if (infoByPackageIdList != null && ApiErrorCode.SUCCESS.getCode() == infoByPackageIdList.getCode()) {
            return infoByPackageIdList.getData();
        }

        return Lists.newArrayList();
    }
}
