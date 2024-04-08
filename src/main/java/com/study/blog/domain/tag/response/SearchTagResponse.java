package com.study.blog.domain.tag.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchTagResponse {
    private String name;

    public SearchTagResponse(String name) {
        this.name = name;
    }
}
