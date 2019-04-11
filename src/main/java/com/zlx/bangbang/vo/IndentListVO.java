package com.zlx.bangbang.vo;

import lombok.Data;

import java.util.List;

@Data
public class IndentListVO {
    private List<IndentVO> indentVOS;

    private boolean hasNextPage;

    public IndentListVO(List<IndentVO> indentVOS, boolean hasNextPage) {
        this.indentVOS = indentVOS;
        this.hasNextPage = hasNextPage;
    }

    public IndentListVO() {
    }
}
