package com.study.blog.presentation.controller.request;

import com.study.blog.business.post.data.PostSearchData;
import lombok.Getter;

@Getter
public class PostSearchRequest {

    private final Long searchCategoryId;
    private final String searchTitle;
    private final String searchContent;
    private final Boolean searchStatus;

    public PostSearchRequest(Long searchCategoryId,
                             String searchTitle,
                             String searchContent,
                             Boolean searchStatus) {
        this.searchCategoryId = searchCategoryId;
        this.searchTitle = searchTitle;
        this.searchContent = searchContent;
        this.searchStatus = searchStatus;
    }

    public PostSearchData toData(){
        return new PostSearchData(
                this.searchCategoryId,
                this.searchTitle,
                this.searchContent,
                this.searchStatus);
    }
}
