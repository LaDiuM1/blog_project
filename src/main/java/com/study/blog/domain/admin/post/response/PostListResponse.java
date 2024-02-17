package com.study.blog.domain.admin.post.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostListResponse {

    private Long id;
    private String title;
    private Boolean status;
    private String categoryName;
    private Set<String> tagNames = new HashSet<>();
    private Long commentCount;

    public PostListResponse(Long id, String title, Boolean status, String categoryName, Long commentCount) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.categoryName = categoryName;
        this.commentCount = commentCount;
    }
}
