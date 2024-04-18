package com.study.blog.domain.comment.repository;

import com.study.blog.domain.common.BaseTime;
import com.study.blog.domain.post.repository.Post;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@DynamicInsert
@DynamicUpdate
@Getter
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_comment_id")
    private Long parentCommentId;

    @Column(nullable = false)
    private String commentContent;

    @Column(nullable = false)
    private String commentAuthorName;

    @Column(nullable = false)
    private String commentAuthorEmail;

    @Column(nullable = false)
    private boolean status = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {}

    public Comment(Post post) {
        this.post = post;
    }

    public void unlinkPost() {
        this.post = null;
    }

    public void switchStatus() {
        this.status = !this.status;
    }
}
