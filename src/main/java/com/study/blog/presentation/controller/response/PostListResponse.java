package com.study.blog.presentation.controller.response;

import com.study.blog.presentation.controller.response.TagResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostListResponse {
    private Long id;
    private String title;
    private String content;
    private Boolean status;
    private String categoryName;
    private Long commentCount;
    @Setter
    private List<TagResponse> tags;

    public PostListResponse(Long id, String title, String content, Boolean status, String categoryName, Long commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.categoryName = categoryName;
        this.commentCount = commentCount;
    }


}
