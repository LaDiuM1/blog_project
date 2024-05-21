package com.study.blog.business.tag;

import com.study.blog.business.tag.dto.SearchTagDto;
import com.study.blog.business.tag.repository.TagRepository;
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
