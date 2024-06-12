package com.study.blog.business.post.exception;

import com.study.blog.support.exception.NotFoundException;

public class PostNotFoundException extends NotFoundException {
    public PostNotFoundException() {
        super("게시글");
    }
}
