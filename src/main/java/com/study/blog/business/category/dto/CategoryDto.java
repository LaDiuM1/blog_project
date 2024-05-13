package com.study.blog.business.category.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CategoryDto {
    private long id;
    private String name;
    private String description;
    private boolean status;
    private int sequence;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updateDate;

    public CategoryDto(long id, String name, String description, boolean status, int sequence, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.sequence = sequence;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

}
