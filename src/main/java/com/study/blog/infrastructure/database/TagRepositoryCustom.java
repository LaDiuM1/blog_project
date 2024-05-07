package com.study.blog.infrastructure.database;

import com.study.blog.presentation.controller.response.TagResponse;
import com.study.blog.presentation.controller.response.SearchTagResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagResponse> searchTag(String name);

    Map<Long, List<TagResponse>> getPostIdAndTagMap(Set<Long> postIds);
}
