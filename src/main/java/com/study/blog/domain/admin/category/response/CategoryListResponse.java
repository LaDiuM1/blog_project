package com.study.blog.domain.admin.category.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

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
