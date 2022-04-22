package com.marketing.activity.handler;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.marketing.activity.domain.dto.ExamDirectoryInfoDTO;
import com.marketing.activity.remote.ExamDirectoryRemoteService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@Component
public class ExamDirectoryHandler {

    private final ExamDirectoryRemoteService examDirectoryRemoteService;

    public ExamDirectoryHandler(ExamDirectoryRemoteService examDirectoryRemoteService) {
        this.examDirectoryRemoteService = examDirectoryRemoteService;
    }

    public List<ExamDirectoryInfoDTO> getExamDirectoryListByIds(List<Integer> directoryIds) {
        if (CollUtil.isEmpty(directoryIds)) {
            return Lists.newArrayList();
        }
        return examDirectoryRemoteService.getExamDirectoryInfoByIds(directoryIds);
    }

    public Map<Integer, String> getExamDirectoryValueMap(List<Integer> directoryIds) {
        if (CollUtil.isEmpty(directoryIds)) {
            return Maps.newHashMap();
        }
        List<ExamDirectoryInfoDTO> list = this.getExamDirectoryListByIds(directoryIds);
        if (CollUtil.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream()
                .collect(Collectors.toMap(ExamDirectoryInfoDTO::getId, ExamDirectoryInfoDTO::getValue, (v1, v2) -> v1));
    }

    public Map<Integer, ExamDirectoryInfoDTO> getExamDirectoryMap(List<Integer> directoryIds) {
        if (CollUtil.isEmpty(directoryIds)) {
            return Maps.newHashMap();
        }
        List<ExamDirectoryInfoDTO> list = this.getExamDirectoryListByIds(directoryIds);
        if (CollUtil.isEmpty(list)) {
            return Maps.newHashMap();
        }
        return list.stream()
                .collect(Collectors.toMap(ExamDirectoryInfoDTO::getId, Function.identity(), (v1, v2) -> v1));
    }

    public ExamDirectoryInfoDTO getExamDirectoryInfo(Integer directoryId) {
        if (directoryId == null || directoryId == 0) {
            return null;
        }
        Map<Integer, ExamDirectoryInfoDTO> examDirectoryMap = this.getExamDirectoryMap(Lists.newArrayList(directoryId));
        if (MapUtils.isEmpty(examDirectoryMap)) {
            return null;
        }

        return examDirectoryMap.get(directoryId);
    }
}
