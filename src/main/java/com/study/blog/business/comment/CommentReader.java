package com.study.blog.business.comment;

import com.study.blog.business.comment.data.SearchCommentData;
import com.study.blog.business.comment.dto.CommentListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentReader {
    private final CommentRepository commentRepository;

    public Page<CommentListDto> searchCommentList(SearchCommentData searchCommentData, Pageable pageable){
        return commentRepository.searchCommentList(searchCommentData, pageable);
    }

}
