package com.study.blog.domain.admin.tag.service;

import com.study.blog.domain.admin.tag.response.SearchTagResponse;
import com.study.blog.infrastructure.persistence.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminTagService {

    private final TagRepository tagRepository;

    public List<SearchTagResponse> searchTag(String searchTagKeyword) {

        return tagRepository.searchTag(searchTagKeyword);

    }

}