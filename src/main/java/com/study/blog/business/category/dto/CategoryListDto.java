package com.study.blog.business.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryListDto {

    private long id;
    private String name;
    private String description;
    private boolean status;
    private int sequence;

    public CategoryListDto(
            long id,
            String name,
            String description,
            boolean status,
            int sequence) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.sequence = sequence;
    }

}
