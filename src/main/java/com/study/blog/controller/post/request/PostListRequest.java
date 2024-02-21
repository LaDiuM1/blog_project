package com.study.blog.controller.post.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PostListRequest {
    private Set<Long> searchCategoryIds;
    private String searchKeyword;
    private Boolean searchStatus;

}
