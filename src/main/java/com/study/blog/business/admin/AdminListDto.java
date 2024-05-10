package com.study.blog.business.admin;

import lombok.Getter;

@Getter
public class AdminListDto {
    private final Long id;
    private final String email;
    private final String name;
    private final boolean status;

    public AdminListDto(Long id, String email, String name, boolean status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = status;
    }
}
