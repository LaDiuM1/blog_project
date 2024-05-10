package com.study.blog.business.comment;

import com.study.blog.business.comment.data.SearchCommentData;
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

    public Page<CommentListDto> searchCommentList(SearchCommentData searchCommentData, Pageable pageable){
        return commentReader.searchCommentList(searchCommentData, pageable);
    }

    public void switchCommentStatus(Long id){
        commentUpdater.switchCommentStatus(id);
    }

}
