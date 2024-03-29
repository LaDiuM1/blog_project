package com.study.blog.persistence.repository.tag;

import com.study.blog.persistence.repository.tag.response.TagResponse;
import com.study.blog.persistence.repository.tag.response.SearchTagResponse;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagResponse> searchTag(String name);

    Map<Long, List<TagResponse>> getPostIdAndTagMap(Set<Long> postIds);
}
