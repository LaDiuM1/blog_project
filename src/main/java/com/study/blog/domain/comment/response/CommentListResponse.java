package com.study.blog.domain.comment.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentListResponse {

    private Long id;
    private Long parentCommentId;
    private String commentContent;
    private String commentAuthorName;
    private String commentAuthorEmail;
    private boolean status;

    private Long postId;
    private String postTitle;

    private String parentCommentContent;

    public CommentListResponse(Long id, Long parentCommentId, String commentContent, String commentAuthorName, String commentAuthorEmail, boolean status, Long postId, String postTitle, String parentCommentContent) {
        this.id = id;
        this.parentCommentId = parentCommentId;
        this.commentContent = commentContent;
        this.commentAuthorName = commentAuthorName;
        this.commentAuthorEmail = commentAuthorEmail;
        this.status = status;
        this.postId = postId;
        this.postTitle = postTitle;
        this.parentCommentContent = parentCommentContent;
    }
}
