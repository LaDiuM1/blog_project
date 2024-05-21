package com.study.blog.business.post.repository;

import com.study.blog.business.post.data.PostSearchData;
import com.study.blog.business.post.dto.PostListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<PostListDto> searchPostList(PostSearchData searchData, Pageable pageable);
}
