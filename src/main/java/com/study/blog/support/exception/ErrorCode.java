package com.study.blog.support.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    RESOURCE_NOT_FOUND("ERR001", "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR("ERR002", "내부 서버 오류입니다."),
    INVALID_REQUEST("ERR003", "잘못된 요청입니다."),
    UNAUTHORIZED("ERR004", "잘못된 접근입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
