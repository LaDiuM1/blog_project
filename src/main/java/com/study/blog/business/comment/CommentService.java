package com.study.blog.business.comment;

import com.study.blog.presentation.controller.response.CommentListResponse;
import com.study.blog.presentation.controller.request.CommentListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ReadComment readComment;
    private final UpdateComment updateComment;

    public Page<CommentListResponse> searchCommentList(CommentListRequest request, Pageable pageable){
        return readComment.searchCommentList(request, pageable);
    }

    public void switchCommentStatus(Long id){
        updateComment.switchCommentStatus(id);
    }

}
