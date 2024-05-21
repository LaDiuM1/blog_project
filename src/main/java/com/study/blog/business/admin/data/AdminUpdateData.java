package com.study.blog.business.admin.data;

import lombok.Getter;

@Getter
public class AdminUpdateData {

    private final String password;
    private final String adminName;

    public AdminUpdateData(String password, String adminName) {
        this.password = password;
        this.adminName = adminName;
    }
}
