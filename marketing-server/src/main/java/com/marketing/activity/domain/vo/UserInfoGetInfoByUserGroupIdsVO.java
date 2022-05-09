package com.marketing.activity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 查询条件
 * @author yyz
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoGetInfoByUserGroupIdsVO implements Serializable {
    /**
     * 学员Id.
     */
    private List<Integer> userGroupIds;
}
