package com.study.blog.business.category.data;

import lombok.Getter;

@Getter
public class CategoryCreateData {

    private final String name;
    private final String description;

    public CategoryCreateData(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
