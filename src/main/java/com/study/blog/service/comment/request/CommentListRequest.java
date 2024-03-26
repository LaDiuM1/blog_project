package com.study.blog.service.comment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListRequest {
    private String searchKeyword;
    private Boolean searchStatus;
}
