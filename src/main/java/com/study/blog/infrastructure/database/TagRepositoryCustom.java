package com.study.blog.infrastructure.database;

import com.study.blog.business.tag.dto.TagDto;
import com.study.blog.business.tag.dto.SearchTagDto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TagRepositoryCustom {
    List<SearchTagDto> searchTag(String name);

    Map<Long, List<TagDto>> getPostIdAndTagMap(Set<Long> postIds);
}
