package com.study.blog.presentation.controller.request;

import com.study.blog.business.comment.data.CommentSearchData;
import lombok.Getter;

@Getter
public class CommentSearchRequest {

    private final String searchContent;
    private final Boolean searchStatus;

    public CommentSearchRequest(String searchContent, Boolean searchStatus) {
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }

    public CommentSearchData toData(){
        return new CommentSearchData(this.searchContent, this.searchStatus);
    }
}
