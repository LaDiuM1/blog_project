package com.study.blog.presentation.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPostRequest {
    private Long searchCategoryId;
    private String searchTitle;
    private String searchContent;
    private Boolean searchStatus;

    public SearchPostRequest(Long searchCategoryId, String searchTitle, String searchContent,Boolean searchStatus) {
        this.searchCategoryId = searchCategoryId;
        this.searchTitle = searchTitle;
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }
}
