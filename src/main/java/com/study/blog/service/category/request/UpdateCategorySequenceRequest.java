package com.study.blog.service.category.request;

import lombok.Getter;

import java.util.LinkedHashSet;

@Getter
public class UpdateCategorySequenceRequest {

    private LinkedHashSet<Long> idSet;

    public UpdateCategorySequenceRequest(LinkedHashSet<Long> idSet) {
        this.idSet = idSet;
    }
}
