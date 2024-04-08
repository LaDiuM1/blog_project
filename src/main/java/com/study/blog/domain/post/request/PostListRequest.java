package com.study.blog.domain.post.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListRequest {
    private Long searchCategoryId;
    private String searchKeyword;
    private Boolean searchStatus;

}
