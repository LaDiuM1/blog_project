package com.study.blog.business.category.data;

import lombok.Getter;

@Getter
public class CategoryUpdateData {

    private final String name;
    private final String description;

    public CategoryUpdateData(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
