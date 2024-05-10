package com.study.blog.presentation.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class CreatedResponse {
    public static ResponseEntity<Map<String,Long>> response(String domainName, Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("created"+domainName+"Id", id));
    }
}
