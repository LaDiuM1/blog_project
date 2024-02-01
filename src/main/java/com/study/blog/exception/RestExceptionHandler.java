package com.study.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
            String message = Objects.requireNonNull(error.getDefaultMessage()).split("&")[0];
            String errorCode = Objects.requireNonNull(error.getDefaultMessage()).split("&")[1];
            errors.put("Error Code", errorCode);
            errors.put("message", message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        String message = ex.getMessage();
        String errorCode = "10000";

        if(message.contains("Duplicate entry")) {
            errorCode = "10001";
            message = "이미 존재합니다.";
        }

        errors.put("Error Code", errorCode);
        errors.put("message", message);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
