package com.marketing.activity.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@Data
public class ExamDirectoryInfoDTO implements Serializable {

    /**
     * 课程栏目ID
     */
    private Integer id;

    /**
     * 课程栏目名称
     */
    private String value;

    /**
     * 父级目录ID
     */
    private Integer ceLveZuTableId;

    private Integer level;


}
