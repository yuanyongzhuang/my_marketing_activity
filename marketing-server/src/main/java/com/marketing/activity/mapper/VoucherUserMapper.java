package com.marketing.activity.mapper;

import com.marketing.activity.domain.entity.VoucherUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.marketing.activity.domain.param.VoucherReceiveDataPageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户优惠券 Mapper 接口
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
@Mapper
public interface VoucherUserMapper extends BaseMapper<VoucherUser> {

    List<VoucherUser> getReceiveDataPageList(VoucherReceiveDataPageParam pageParam);
}
