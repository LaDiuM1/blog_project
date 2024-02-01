package com.study.blog.infrastructure.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@ToString
@NoArgsConstructor
public class CategoryDto {

    private long id;
    private String name;
    private String description;
    private boolean state;
    private int sequence;

    public CategoryDto(long id, String name, String description, boolean state, int sequence) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.sequence = sequence;
    }
}
