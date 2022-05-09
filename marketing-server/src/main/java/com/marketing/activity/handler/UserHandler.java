package com.marketing.activity.handler;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Sets;
import com.marketing.activity.domain.dto.UserInfoDTO;
import com.marketing.activity.remote.UserRemoteService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 用户信息处理类
 * @author yyz
 */
@Component
public class UserHandler {

    @Resource
    private UserRemoteService userRemoteService;

    public UserInfoDTO getUserInfo(Integer userId) {
        Map<Integer,UserInfoDTO> map = this.getUserMapByUserIds(Sets.newHashSet(userId));
        if(MapUtil.isNotEmpty(map)){
            return null;
        }
        return map.get(userId);
    }

    private Map<Integer, UserInfoDTO> getUserMapByUserIds(HashSet<Integer> userIds) {
        if(CollectionUtil.isEmpty(userIds)){
            return MapUtil.newHashMap();
        }
        List<UserInfoDTO> list = this.getUserListByUserIds(userIds);
        if(CollectionUtil.isEmpty(list)){
            return MapUtil.newHashMap();
        }
        return list.stream().collect(Collectors.toMap(UserInfoDTO::getGroupId, Function.identity(),(v1,v2)->v1));
    }

    public List<UserInfoDTO> getUserListByUserIds(HashSet<Integer> userIds) {
        if(CollectionUtil.isEmpty(userIds)){
            return Lists.newArrayList();
        }
        return userRemoteService.getUserListByUserIds(userIds);
    }

    public UserInfoDTO getUserInfoByMobile(String mobile){
        if(StringUtils.isBlank(mobile)){
            return null;
        }
        return userRemoteService.getUserInfoByMobile(mobile);
    }

}
