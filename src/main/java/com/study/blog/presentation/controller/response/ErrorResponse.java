package com.study.blog.presentation.controller.response;

import com.study.blog.support.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final String code;
    private final String description;
    private final String path;
    private final Map<String, String> details;

    public ErrorResponse(ErrorCode errorCode, String path, LinkedHashMap<String, String> details) {
        this.timestamp = LocalDateTime.now();
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
        this.path = path;
        this.details = new LinkedHashMap<>(details);
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode,
                                                                 WebRequest request,
                                                                 LinkedHashMap<String, String> details) {
        ErrorResponse errorResponse = new ErrorResponse(
                errorCode,
                request.getDescription(false),
                details);

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }
}
