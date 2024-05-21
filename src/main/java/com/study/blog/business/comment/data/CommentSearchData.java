package com.study.blog.business.comment.data;

import lombok.Getter;

@Getter
public class CommentSearchData {

    private final String searchContent;
    private final Boolean searchStatus;

    public CommentSearchData(String searchContent, Boolean searchStatus) {
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }
}
