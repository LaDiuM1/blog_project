package com.study.blog.business.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagReader tagReader;

    public List<SearchTagDto> searchTag(String searchTagKeyword) {

        return tagReader.searchTag(searchTagKeyword);
    }

}
