package com.study.blog.business.tag;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchTagDto {
    private String name;

    public SearchTagDto(String name) {
        this.name = name;
    }
}
