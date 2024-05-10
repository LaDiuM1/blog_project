package com.study.blog.business.comment.data;

import lombok.Getter;

@Getter
public class SearchCommentData {
    private String searchContent;
    private Boolean searchStatus;

    public SearchCommentData(String searchContent, Boolean searchStatus) {
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }


}
