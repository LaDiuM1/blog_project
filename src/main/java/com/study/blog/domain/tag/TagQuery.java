package com.study.blog.domain.tag;

import com.study.blog.domain.tag.repository.TagRepository;
import com.study.blog.domain.tag.response.SearchTagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagQuery {

    private final TagRepository tagRepository;

    public List<SearchTagResponse> searchTag(String searchTagKeyword) {

        return tagRepository.searchTag(searchTagKeyword);
    }

}
