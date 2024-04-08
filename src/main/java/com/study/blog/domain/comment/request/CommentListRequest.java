package com.study.blog.domain.comment.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListRequest {
    private String searchKeyword;
    private Boolean searchStatus;
}
