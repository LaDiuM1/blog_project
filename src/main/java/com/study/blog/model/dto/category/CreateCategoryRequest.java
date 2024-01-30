package com.study.blog.model.dto.category;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class CreateCategoryRequest {

    @NotBlank(message = "Category name cannot be blank")
    @Size(max = 100, message = "Category name must be at most 100 characters")
    private String name;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

}
