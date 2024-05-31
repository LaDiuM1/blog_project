package com.study.blog.business.category.exception;

import com.study.blog.support.exception.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException() {
        super("카테고리");
    }
}
