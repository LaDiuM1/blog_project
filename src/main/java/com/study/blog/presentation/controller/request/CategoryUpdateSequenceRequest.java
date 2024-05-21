package com.study.blog.presentation.controller.request;

import lombok.Getter;

import java.util.LinkedHashSet;

@Getter
public class CategoryUpdateSequenceRequest {

    private final LinkedHashSet<Long> idSet;

    public CategoryUpdateSequenceRequest(LinkedHashSet<Long> idSet) {
        this.idSet = idSet;
    }
}
