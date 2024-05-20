package com.study.blog.business.post.data;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class PostData {
    private Long categoryId;
    private String title;
    private String content;
    private HashSet<String> tagNames;

    public PostData(Long categoryId, String title, String content, HashSet<String> tagNames) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.tagNames = tagNames;
    }
}
