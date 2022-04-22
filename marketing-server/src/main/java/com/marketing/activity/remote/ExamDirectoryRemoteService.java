package com.marketing.activity.remote;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.dto.ExamDirectoryInfoDTO;
import com.marketing.activity.domain.vo.ExamDirectoryIdsVO;
import com.marketing.activity.remote.api.ExamDirectoryFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@Slf4j
@Service
public class ExamDirectoryRemoteService {

    @Resource
    private ExamDirectoryFeignApi examDirectoryFeignApi;

    public List<ExamDirectoryInfoDTO> getExamDirectoryInfoByIds(List<Integer> directoryIds){
        try{
            ExamDirectoryIdsVO examDirectoryIdsVO = ExamDirectoryIdsVO.builder()
                    .directoryIds(directoryIds)
                    .build();
            CommonResult<List<ExamDirectoryInfoDTO>> examDirectoryInfoByIdsResult = examDirectoryFeignApi.getByDirectoryIds(examDirectoryIdsVO);
            if(!examDirectoryInfoByIdsResult.ok()){
                log.info("getExamDirectoryInfoByIds examDirectoryInfoByIdsResult = {}", JSONObject.toJSONString(examDirectoryInfoByIdsResult));
                return Lists.newArrayList();
            }
            return examDirectoryInfoByIdsResult.getData();
        }catch (Exception e){
            log.error("e=",e);
            return Lists.newArrayList();
        }
    }


}
