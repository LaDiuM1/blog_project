package com.study.blog.business.admin.exception;

import com.study.blog.support.exception.AccessDeniedException;

public class AdminAccessDeniedException extends AccessDeniedException {
    public AdminAccessDeniedException() {
        super("해당 계정은 관리자가 아닙니다.");
    }
}
