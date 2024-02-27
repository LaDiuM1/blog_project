package com.study.blog.infrastructure.persistence.repository.category.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryListResponse {

    private long id;
    private String name;
    private String description;
    private boolean status;
    private int sequence;

    public CategoryListResponse(long id, String name, String description, boolean status, int sequence) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.sequence = sequence;
    }

}
