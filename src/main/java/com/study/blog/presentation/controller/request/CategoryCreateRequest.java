package com.study.blog.presentation.controller.request;

import com.study.blog.business.category.data.CategoryCreateData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CategoryCreateRequest {

    @NotBlank
    @Size(max = 100)
    private final String name;

    @NotBlank
    @Size(max = 255)
    private final String description;

    public CategoryCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public CategoryCreateData toData(){
        return new CategoryCreateData(this.name, this.description);
    }
}
