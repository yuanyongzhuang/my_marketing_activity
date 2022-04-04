package com.marketing.activity.mapper;

import com.marketing.activity.domain.entity.VoucherInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.marketing.activity.domain.param.VoucherInfoPageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 券 Mapper 接口
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@Mapper
public interface VoucherInfoMapper extends BaseMapper<VoucherInfo> {

    List<VoucherInfo> selectListByActivityId(VoucherInfoPageParam pageParam);

}
