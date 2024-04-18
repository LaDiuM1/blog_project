package com.study.blog.domain.post.response;

import com.querydsl.core.annotations.QueryProjection;
import com.study.blog.domain.tag.response.TagResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostListResponse {
    private Long id;
    private String title;
    private Boolean status;
    private String categoryName;
    private Long commentCount;
    @Setter
    private List<TagResponse> tags;

    public PostListResponse(Long id, String title, Boolean status, String categoryName, Long commentCount) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.categoryName = categoryName;
        this.commentCount = commentCount;
    }


}
