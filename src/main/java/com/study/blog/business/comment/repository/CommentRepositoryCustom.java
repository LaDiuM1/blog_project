package com.study.blog.business.comment.repository;

import com.study.blog.business.comment.data.CommentSearchData;
import com.study.blog.business.comment.dto.CommentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListDto> searchCommentList(CommentSearchData commentSearchData, Pageable pageable);
}
