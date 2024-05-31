package com.study.blog.business.admin.exception;

import com.study.blog.support.exception.NotFoundException;

public class AdminNotFoundException extends NotFoundException {
    public AdminNotFoundException() {
        super("관리자");
    }
}
