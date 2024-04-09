package com.study.blog.domain.tag.service;

import com.study.blog.domain.tag.response.SearchTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ReadTag readTag;

    public List<SearchTagResponse> searchTag(String searchTagKeyword) {

        return readTag.searchTag(searchTagKeyword);
    }

}
