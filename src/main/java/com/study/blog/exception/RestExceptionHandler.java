package com.study.blog.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String message = error.getDefaultMessage();
            errors.put("message", message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        String message = ex.getMessage();
        String errorCode = "50000";

        if(message.contains("Duplicate entry")) {
            errorCode = "10001";
            message = "이미 존재합니다.";
        }

        errors.put("Error Code", errorCode);
        errors.put("message", message);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("Error Code", "50001");
        errors.put("message", "전송 포맷이 JSON 형태가 아닙니다.");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("Error Code", "50002");
        errors.put("message", "해당 id로 엔티티를 찾을 수 없습니다.");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(IllegalStateException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("Error Code", "50003");
        errors.put("message", "값이 중복되었거나 저장된 카테고리 수와 다릅니다.");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        errors.put("Error Code", "50004");
        errors.put("message", "삭제할 id를 찾을 수 없습니다.");

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }



}
