package com.marketing.activity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询父类.
 *
 * @author L
 */
@ApiModel
@Data
public class BasePageQuery implements Serializable {

    /**
     * 默认页.
     */
    public static final int DEFAULT_PAGE_NO = 1;

    /**
     * 默认大小.
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页.
     */
    @ApiModelProperty("当前页 默认值 1")
    private Integer currentPage;

    /**
     * 查询数量.
     */
    @ApiModelProperty("查询数量 默认值 10")
    private Integer pageSize;

    /**
     * 起始位置.
     */
    @JsonIgnore
    private Integer start;

    @JsonIgnore
    private Integer limit;

    /**
     * 是否需要排序.
     */
    @JsonIgnore
    private Boolean sort;

    public Integer getCurrentPage() {
        if (currentPage == null) {
            currentPage = DEFAULT_PAGE_NO;
        }
        return currentPage;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public Integer getStart() {
        int page = (this.currentPage == null || this.currentPage < 0) ? DEFAULT_PAGE_NO : this.currentPage;
        int size = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;

        start = (page - 1) * size;
        return start;
    }
}
