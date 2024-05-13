package com.study.blog.business.category.data;

import lombok.Getter;

@Getter
public class CategoryData {
    private String name;
    private String description;

    public CategoryData(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
