package com.marketing.activity.mapper;

import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

    List<Integer> queryDistinctColumn();
}
