package com.marketing.activity.handler;

import com.marketing.activity.domain.dto.PackageInfoByPackageIdDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 商品信息处理类
 * @author yyz
 */
@Component
public class ProductHandler {

    @Resource
    private ProductRemoteService productRemoteService;

    public List<PackageInfoByPackageIdDTO> batchGetProductListByIds(Set<Integer> productIds){
        return productRemoteService.getProductListByIds(productIds);
    }
}
