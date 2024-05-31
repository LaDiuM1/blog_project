package com.study.blog.business.admin.exception;

import com.study.blog.support.exception.NotFoundException;

public class AdminNotFoundException extends NotFoundException {
    public AdminNotFoundException() {
        super("해당 관리자를 찾을 수 없습니다.");
    }
}
