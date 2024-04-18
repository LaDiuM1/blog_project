package com.study.blog.domain.comment.request;

import lombok.Getter;

@Getter
public class CommentListRequest {
    private String searchContent;
    private Boolean searchStatus;

    public CommentListRequest(String searchContent, Boolean searchStatus) {
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }
}
