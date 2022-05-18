package com.marketing.activity.mapper.mysql.marketingActivity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 券活动 Mapper 接口
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
@Mapper
public interface VoucherActivityInfoMapper extends BaseMapper<VoucherActivityInfo> {

    /**
     * 获取活动的所属分类
     * @return list
     */
    List<Integer> queryDistinctColumn();

    /**
     * 通过分类查询领券列表
     * @param page page
     * @param examGroupId columnId
     * @return list
     */
    List<VoucherActivityInfo> queryListByColumnId(Page<VoucherActivityInfo> page, @Param("columnId") Integer examGroupId);
}
