package com.marketing.activity.remote;

import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.dto.UserInfoDTO;
import com.marketing.activity.domain.vo.UserInfoGetInfoByUserGroupIdsVO;
import com.marketing.activity.remote.api.UserFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 学员用户远程服务
 * @author yyz
 */
@Component
@Slf4j
public class UserRemoteService {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 根据手机号获取学员信息
     * @param mobile
     * @return
     */
    public UserInfoDTO getUserInfoByMobile(String mobile){
        try{
            CommonResult<UserInfoDTO> result = userFeignApi.getUserInfoByMobile(mobile);
            if(!result.ok()){
                log.info("getUserInfoByMobile result={}", JSONObject.toJSONString(result));
                return null;
            }
            return result.getData();
        }catch (Exception e){
            log.error("getUserInfoByMobile e=",e);
            return null;
        }
    }

    /**
     * 根据userIds查询用户信息
     * @param userIds userIds
     * @return list
     */
    public List<UserInfoDTO> getUserListByUserIds(Set<Integer> userIds){
        try{
            UserInfoGetInfoByUserGroupIdsVO vo = new UserInfoGetInfoByUserGroupIdsVO();
            vo.setUserGroupIds(Lists.newArrayList(userIds));
            CommonResult<List<UserInfoDTO>> result = userFeignApi.getUserInfoByUserGroupIds(vo);
            if(!result.ok()){
                log.info("getUserListByUserIds result={}",JSONObject.toJSONString(result));
                return Lists.newArrayList();
            }
            return result.getData();
        }catch(Exception e){
            log.error("getUserListByUserIds e=",e);
            return Lists.newArrayList();
        }
    }

}
