package com.study.blog.business.tag;

import com.study.blog.business.tag.TagRepository;
import com.study.blog.presentation.controller.response.SearchTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReadTag {

    private final TagRepository tagRepository;

    public List<SearchTagResponse> searchTag(String searchTagKeyword) {

        return tagRepository.searchTag(searchTagKeyword);
    }

}
