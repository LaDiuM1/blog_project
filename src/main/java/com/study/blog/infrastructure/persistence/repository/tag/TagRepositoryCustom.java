package com.study.blog.infrastructure.persistence.repository.tag;

import com.study.blog.domain.admin.tag.response.SearchTagResponse;

import java.util.List;

public interface TagRepositoryCustom {
    List<SearchTagResponse> searchTag(String name);

}
