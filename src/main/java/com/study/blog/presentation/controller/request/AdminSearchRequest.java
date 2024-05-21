package com.study.blog.presentation.controller.request;

import com.study.blog.business.admin.data.AdminSearchData;
import lombok.Getter;

@Getter
public class AdminSearchRequest {

    private final String searchEmail;
    private final String searchName;
    private final Boolean searchStatus;

    public AdminSearchRequest(String searchEmail, String searchName, Boolean searchStatus) {
        this.searchEmail = searchEmail;
        this.searchName = searchName;
        this.searchStatus = searchStatus;
    }

    public AdminSearchData toData() {
        return new AdminSearchData(
                this.searchEmail,
                this.searchName,
                this.searchStatus
        );

    }
}
