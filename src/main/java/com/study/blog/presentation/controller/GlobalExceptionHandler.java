package com.study.blog.presentation.controller;

import com.study.blog.presentation.controller.response.ErrorResponse;
import com.study.blog.support.exception.ErrorCode;
import com.study.blog.support.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        LinkedHashMap<String, String> details = new LinkedHashMap<>();
        details.put("message", ex.getMessage());

        return ErrorResponse.toResponseEntity(
                ErrorCode.RESOURCE_NOT_FOUND,
                request,
                details
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        LinkedHashMap<String, String> details = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });

        return ErrorResponse.toResponseEntity(
                ErrorCode.INVALID_REQUEST,
                request,
                details
        );
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseConflictException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        LinkedHashMap<String, String> details = new LinkedHashMap<>();
        String message = "데이터 충돌이 발생하였습니다.";

        int sqlErrorCode = ex.getErrorCode();
        if(sqlErrorCode == 1062){ message = "중복된 값이 존재합니다. 이미 사용 중인 id일 수 있습니다."; }

        details.put("message", message);

        return ErrorResponse.toResponseEntity(
                ErrorCode.DATABASE_CONFLICT_ERROR,
                request,
                details
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException ex, WebRequest request) {
        LinkedHashMap<String, String> details = new LinkedHashMap<>();
        String message = "이메일 또는 비밀번호가 일치하지 않습니다.";
        details.put("message", message);

        return ErrorResponse.toResponseEntity(
                ErrorCode.UNAUTHORIZED,
                request,
                details
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        LinkedHashMap<String, String> details = new LinkedHashMap<>();
        details.put("message", ex.getMessage());

        return ErrorResponse.toResponseEntity(
                ErrorCode.INVALID_REQUEST,
                request,
                details
        );
    }

}
