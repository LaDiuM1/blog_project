package com.study.blog.presentation.controller.response;

import com.study.blog.support.exception.ErrorCode;

import java.util.LinkedHashMap;

public class ErrorResponse {
    private final ErrorCode code;
    private final LinkedHashMap<String, String> messages;

    public ErrorResponse(ErrorCode code, LinkedHashMap<String, String> messages) {
        this.code = code;
        this.messages = messages;
    }

}
