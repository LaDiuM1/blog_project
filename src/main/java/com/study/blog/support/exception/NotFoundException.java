package com.study.blog.support.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String category;

    public NotFoundException(String category) {
        super(category+"의 id를 찾을 수 없습니다.");
        this.category = category;
    }

}
