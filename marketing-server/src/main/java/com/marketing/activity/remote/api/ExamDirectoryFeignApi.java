package com.marketing.activity.remote.api;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.dto.ExamDirectoryInfoDTO;
import com.marketing.activity.domain.vo.ExamDirectoryIdsVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@FeignClient(value = "zhongye-support", path = "/examDirectory")
public interface ExamDirectoryFeignApi {

    @PostMapping("/getByDirectoryIds")
    CommonResult<List<ExamDirectoryInfoDTO>> getByDirectoryIds(@RequestBody ExamDirectoryIdsVO examDirectoryIdsVO);
}
