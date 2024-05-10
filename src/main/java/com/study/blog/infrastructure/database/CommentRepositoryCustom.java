package com.study.blog.infrastructure.database;

import com.study.blog.business.comment.data.SearchCommentData;
import com.study.blog.business.comment.dto.CommentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListDto> searchCommentList(SearchCommentData searchCommentData, Pageable pageable);
}
