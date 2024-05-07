package com.study.blog.presentation.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAdminRequest {
    private String searchEmail;
    private String searchName;
    private Boolean searchStatus;

    public SearchAdminRequest(String searchEmail, String searchName, Boolean searchStatus) {
        this.searchEmail = searchEmail;
        this.searchName = searchName;
        this.searchStatus = searchStatus;
    }
}
