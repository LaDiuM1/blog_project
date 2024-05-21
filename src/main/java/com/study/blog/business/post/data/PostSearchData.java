package com.study.blog.business.post.data;

import lombok.Getter;

@Getter
public class PostSearchData {

    private final Long searchCategoryId;
    private final String searchTitle;
    private final String searchContent;
    private final Boolean searchStatus;

    public PostSearchData(Long searchCategoryId, String searchTitle, String searchContent, Boolean searchStatus) {
        this.searchCategoryId = searchCategoryId;
        this.searchTitle = searchTitle;
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }
}
