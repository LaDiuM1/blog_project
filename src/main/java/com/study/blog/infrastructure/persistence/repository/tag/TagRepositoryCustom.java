package com.study.blog.infrastructure.persistence.repository.tag;

import com.study.blog.service.tag.response.SearchTagResponse;
import com.study.blog.service.tag.response.TagResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagResponse> searchTag(String name);

    Map<Long, List<TagResponse>> getPostIdAndTagMap(Set<Long> postIds);
}
