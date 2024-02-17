package com.study.blog.domain.admin.post.request;

import lombok.Getter;

import java.util.Set;

@Getter
public class PostListRequest {
    private Set<Long> searchCategoryIds;
    private String searchKeyword;
    private Boolean searchStatus;

}
