package com.study.blog.business.admin.data;

import lombok.Getter;

@Getter
public class AdminSearchData {

    private final String searchEmail;
    private final String searchName;
    private final Boolean searchStatus;

    public AdminSearchData(String searchEmail, String searchName, Boolean searchStatus) {
        this.searchEmail = searchEmail;
        this.searchName = searchName;
        this.searchStatus = searchStatus;
    }
}
