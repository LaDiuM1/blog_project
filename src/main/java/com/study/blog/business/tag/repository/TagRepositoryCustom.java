package com.study.blog.business.tag.repository;

import com.study.blog.business.tag.dto.SearchTagDto;
import com.study.blog.business.tag.dto.TagDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagDto> searchTag(String name);

    Map<Long, List<TagDto>> getPostIdAndTagMap(Set<Long> postIds);
}
