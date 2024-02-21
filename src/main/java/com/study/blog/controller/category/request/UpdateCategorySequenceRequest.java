package com.study.blog.controller.category.request;

import lombok.Getter;

import java.util.LinkedHashSet;

@Getter
public class UpdateCategorySequenceRequest {

    private LinkedHashSet<Long> idSet;

}
