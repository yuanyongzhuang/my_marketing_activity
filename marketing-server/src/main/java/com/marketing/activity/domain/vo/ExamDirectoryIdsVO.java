package com.marketing.activity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDirectoryIdsVO implements Serializable {

    /**
     * 项目/课程Id集合
     */
    private List<Integer> directoryIds;

}
