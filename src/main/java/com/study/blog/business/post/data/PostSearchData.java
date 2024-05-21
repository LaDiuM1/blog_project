package com.study.blog.business.post.data;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class PostSearchData {

    private final Long categoryId;
    private final String title;
    private final String content;
    private final HashSet<String> tagNames;

    public PostSearchData(Long categoryId, String title, String content, HashSet<String> tagNames) {
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.tagNames = tagNames;
    }
}
