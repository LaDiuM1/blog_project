package com.study.blog.business.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ReadTag readTag;

    public List<SearchTagDto> searchTag(String searchTagKeyword) {

        return readTag.searchTag(searchTagKeyword);
    }

}
