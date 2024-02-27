package com.study.blog.service.post.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PostListRequest {
    private Long searchCategoryId;
    private String searchKeyword;
    private Boolean searchStatus;

}
