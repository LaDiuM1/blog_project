package com.study.blog.business.admin.data;

import lombok.Getter;

@Getter
public class SearchAdminData {
    private String searchEmail;
    private String searchName;
    private Boolean searchStatus;

    public SearchAdminData(String searchEmail, String searchName, Boolean searchStatus) {
        this.searchEmail = searchEmail;
        this.searchName = searchName;
        this.searchStatus = searchStatus;
    }
}
