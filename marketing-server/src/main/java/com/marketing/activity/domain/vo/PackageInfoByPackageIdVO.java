package com.marketing.activity.domain.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yyz
 */
@Data
@Builder
public class PackageInfoByPackageIdVO {
    /**
     * 套餐ID集合
     */
    private List<Integer> packageIdList;
}
