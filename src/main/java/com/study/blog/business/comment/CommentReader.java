package com.study.blog.business.comment;

import com.study.blog.business.comment.data.CommentSearchData;
import com.study.blog.business.comment.dto.CommentListDto;
import com.study.blog.business.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {

    private final CommentRepository commentRepository;

    public Page<CommentListDto> searchCommentList(CommentSearchData commentSearchData, Pageable pageable){
        return commentRepository.searchCommentList(commentSearchData, pageable);
    }

}
