package com.study.blog.business.category.data;

import lombok.Getter;

@Getter
public class UpdateCategoryData {
    private String name;
    private String description;

    public UpdateCategoryData(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
