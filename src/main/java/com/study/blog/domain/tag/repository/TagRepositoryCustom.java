package com.study.blog.domain.tag.repository;

import com.study.blog.domain.tag.response.TagResponse;
import com.study.blog.domain.tag.response.SearchTagResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagResponse> searchTag(String name);

    Map<Long, List<TagResponse>> getPostIdAndTagMap(Set<Long> postIds);
}
