package com.study.blog.infrastructure.persistence.repository.tag;

import com.study.blog.domain.admin.tag.response.SearchTagResponse;
import com.study.blog.domain.admin.tag.response.TagResponse;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagResponse> searchTag(String name);

    Map<Long, List<TagResponse>> getPostIdAndTagMap(Set<Long> postIds);
}
