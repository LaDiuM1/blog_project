package com.study.blog.presentation.controller.response;

import lombok.Getter;

@Getter
public class AdminListResponse {
    private final Long id;
    private final String email;
    private final String name;
    private final boolean status;

    public AdminListResponse(Long id, String email, String name, boolean status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = status;
    }
}
