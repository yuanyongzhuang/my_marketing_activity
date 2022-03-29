package com.marketing.activity.base;

//import com.github.pagehelper.Page;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommonPage<T> implements Serializable {

    @ApiModelProperty(value = "当前页码", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "每页数量", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "总页数", required = true)
    private Integer totalPage;

    private List<T> list;

    @ApiModelProperty(value = "总数量", required = true)
    private long total;

    public static <T> CommonPage<T> empty() {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotal(0L);
        result.setTotalPage(0);
        result.setPageNum(0);
        result.setPageSize(0);
        result.setList(Lists.newArrayList());
        return result;
    }

    public static <T> CommonPage<T> restPage(Long total, Integer totalPage, Integer pageNum, Integer pageSize) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotal(total);
        result.setTotalPage(totalPage);
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        result.setList(Lists.newArrayList());
        return result;
    }

//    /**
//     * 将PageHelper分页后的list转为分页信息
//     */
//    public static <T> CommonPage<T> restPage(List<T> list, Page<Object> pageInfo) {
//        CommonPage<T> result = new CommonPage<T>();
//        result.setTotalPage(pageInfo.getPages());
//        result.setPageNum(pageInfo.getPageNum());
//        result.setPageSize(pageInfo.getPageSize());
//        result.setTotal(pageInfo.getTotal());
//        result.setList(list);
//        return result;
//    }

}
