package com.study.blog.support.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    RESOURCE_NOT_FOUND("ERR001", "Resource not found", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("ERR002", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("ERR003", "Invalid request", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("ERR004", "Unauthorized access", HttpStatus.UNAUTHORIZED),
    DATABASE_ACCESS_ERROR("ERR005", "DB access error", HttpStatus.BAD_REQUEST),
    DATABASE_CONFLICT_ERROR("ERR006", "DB Conflict error", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String description;
    private final HttpStatus status;

    ErrorCode(String code, String description, HttpStatus status) {
        this.code = code;
        this.description = description;
        this.status = status;
    }

}
