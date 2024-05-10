package com.study.blog.presentation.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class SuccessfulResponse {
    public static <T> ResponseEntity<T> response(T responseDto) {
        return ResponseEntity.ok(responseDto);
    }
}
