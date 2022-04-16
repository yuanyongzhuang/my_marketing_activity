package com.marketing.activity.helper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.mapper.VoucherUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户券工具
 * @author yyz
 */
@Component
public class VoucherUserHelper {

    @Resource
    private VoucherUserMapper voucherUserMapper;

    public List<VoucherUser> getAllList(Long userId) {
        LambdaQueryWrapper<VoucherUser> voucherUserLambdaQueryWrapper = Wrappers.lambdaQuery(VoucherUser.class);
        voucherUserLambdaQueryWrapper.eq(VoucherUser::getDeleteStatus, EnabledStatusEnum.NO.getValue());
        voucherUserLambdaQueryWrapper.eq(VoucherUser::getUserId, userId);
        return voucherUserMapper.selectList(voucherUserLambdaQueryWrapper);
    }
}
