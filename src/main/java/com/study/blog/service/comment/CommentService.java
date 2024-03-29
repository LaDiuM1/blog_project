package com.study.blog.service.comment;

import com.study.blog.persistence.entity.Comment;
import com.study.blog.persistence.repository.comment.CommentRepository;
import com.study.blog.persistence.repository.comment.response.CommentListResponse;
import com.study.blog.service.comment.request.CommentListRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Page<CommentListResponse> searchCommentList(CommentListRequest request, Pageable pageable){
        String searchKeyword = request.getSearchKeyword();
        Boolean searchStatus = request.getSearchStatus();

        return commentRepository.getCommentList(searchKeyword, searchStatus, pageable);
    }

    @Transactional
    public void updateCommentStatus(Long id){
        Comment comment = commentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        comment.setStatus(!comment.isStatus());
    }

}
