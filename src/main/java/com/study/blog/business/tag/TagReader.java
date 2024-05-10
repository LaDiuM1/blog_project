package com.study.blog.business.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagReader {

    private final TagRepository tagRepository;

    public List<SearchTagDto> searchTag(String searchTagKeyword) {

        return tagRepository.searchTag(searchTagKeyword);
    }

}
