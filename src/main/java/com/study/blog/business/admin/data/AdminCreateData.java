package com.study.blog.business.admin.data;

import lombok.Getter;

@Getter
public class AdminCreateData {

    private final String email;
    private final String password;
    private final String adminName;

    public AdminCreateData(String email, String password, String adminName) {
        this.email = email;
        this.password = password;
        this.adminName = adminName;
    }
}
