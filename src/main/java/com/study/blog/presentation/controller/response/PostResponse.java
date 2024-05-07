package com.study.blog.presentation.controller.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private Set<String> tagNames;

    public PostResponse(Long id, String title, String content, Set<String> tagNames) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tagNames = tagNames;
    }
}
