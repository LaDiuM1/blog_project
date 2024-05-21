package com.study.blog.presentation.controller.request;

import lombok.Getter;

@Getter
public class PostSearchRequest {

    private final Long searchCategoryId;
    private final String searchTitle;
    private final String searchContent;
    private final Boolean searchStatus;

    public PostSearchRequest(Long searchCategoryId, String searchTitle, String searchContent, Boolean searchStatus) {
        this.searchCategoryId = searchCategoryId;
        this.searchTitle = searchTitle;
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }
}
