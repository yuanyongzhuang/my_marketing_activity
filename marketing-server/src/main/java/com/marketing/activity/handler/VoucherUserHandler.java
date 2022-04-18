package com.marketing.activity.handler;

import com.marketing.activity.domain.entity.VoucherUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户券处理器
 * </p>
 *
 * @author yyz
 * @since 2022/4/18
 */
@Slf4j
@Component
public class VoucherUserHandler {

    /**
     * 过滤券
     * @param voucherUserList 券
     * @return obj
     */
    public List<VoucherUser> filterAvailableVoucher(List<VoucherUser> voucherUserList) {
        //
    }

    //    /**
//     * 过滤券.
//     *
//     * @param userVoucherList 券
//     * @return pair
//     */
//    public Pair<List<VoucherUser>/* 可用 */, /* 已过期 */List<VoucherUser>> filterVoucher(List<VoucherUser> userVoucherList) {
//
//        // filter used voucher
//        List<VoucherUser> filterCanUseList = userVoucherList.stream().filter(info -> info.getUseStatus() == 0).collect(Collectors.toList());
//
//        Date accessTime = BaseContextHandler.getAccessTime() == null ? new Date() : BaseContextHandler.getAccessTime();
//
//        // available
//        List<VoucherUser> availableList = Lists.newArrayList();
//        // expire
//        List<VoucherUser> expireList = Lists.newArrayList();
//
//        for (VoucherUser info : filterCanUseList) {
//            if (info.getExpireTime().before(accessTime)) {
//                // expire
//                expireList.add(info);
//            } else {
//                // available
//                availableList.add(info);
//            }
//        }
//
//        return new Pair<>(availableList, expireList);
//    }
}
