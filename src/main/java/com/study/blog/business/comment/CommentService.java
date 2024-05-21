package com.study.blog.business.comment;

import com.study.blog.business.comment.data.CommentSearchData;
import com.study.blog.business.comment.dto.CommentListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentReader commentReader;
    private final CommentUpdater commentUpdater;

    public Page<CommentListDto> searchCommentList(CommentSearchData commentSearchData, Pageable pageable){
        return commentReader.searchCommentList(commentSearchData, pageable);
    }

    public void switchCommentStatus(Long commentId){
        commentUpdater.switchCommentStatus(commentId);
    }

}
