package com.study.blog.business.category.data;

import lombok.Getter;

import java.util.LinkedHashSet;

@Getter
public class CategoryUpdateSequenceData {

    private final LinkedHashSet<Long> idSet;

    public CategoryUpdateSequenceData(LinkedHashSet<Long> idSet) {
        this.idSet = idSet;
    }

}
