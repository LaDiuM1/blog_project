package com.study.blog.business.post.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private Set<String> tagNames;
    @QueryProjection
    public PostDto(Long id, String title, String content, Set<String> tagNames) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tagNames = tagNames;
    }
}
