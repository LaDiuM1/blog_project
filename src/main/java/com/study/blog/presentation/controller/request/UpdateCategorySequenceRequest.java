package com.study.blog.presentation.controller.request;

import lombok.Getter;

import java.util.LinkedHashSet;

@Getter
public class UpdateCategorySequenceRequest {

    private LinkedHashSet<Long> idSet;

    public UpdateCategorySequenceRequest(LinkedHashSet<Long> idSet) {
        this.idSet = idSet;
    }
}
