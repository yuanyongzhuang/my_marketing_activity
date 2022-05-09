package com.marketing.activity.remote.api;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.dto.UserInfoDTO;
import com.marketing.activity.domain.vo.UserInfoGetInfoByUserGroupIdsVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 用户 接口
 * @author yyz
 */
@FeignClient(value = "zhongye-support", path = "/user")
public interface UserFeignApi {

    /**
     * 根据手机号获取学员信息
     *
     * @param mobile 手机号
     * @return obj
     */
    @GetMapping("/getUserInfoByMobile")
    CommonResult<UserInfoDTO> getUserInfoByMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据userIds 批量获取user
     *
     * @param params 请求参数
     * @return obj
     */
    @PostMapping("/getUserInfoByUserGroupIds")
    CommonResult<List<UserInfoDTO>> getUserInfoByUserGroupIds(@RequestBody UserInfoGetInfoByUserGroupIdsVO params);
}
