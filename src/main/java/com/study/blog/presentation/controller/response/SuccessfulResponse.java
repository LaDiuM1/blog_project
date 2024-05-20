package com.study.blog.presentation.controller.response;

import org.springframework.http.ResponseEntity;

public class SuccessfulResponse {
    public static <T> ResponseEntity<T> response(T responseDto) {
        return ResponseEntity.ok(responseDto);
    }
}
