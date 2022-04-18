package com.marketing.activity.remote.api;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.dto.PackageInfoByPackageIdDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/18
 */
@FeignClient(value = "zhongye-product",path = "/package")
public interface ProductFeignApi {

    @PostMapping("/getInfoByPackageIdList")
    CommonResult<List<PackageInfoByPackageIdDTO>> getInfoByPackageIdList(@RequestBody LinkedHashMap<String,String> params);
}
