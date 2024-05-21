package com.study.blog.business.post;

import com.study.blog.business.BaseTime;
import com.study.blog.business.category.Category;
import com.study.blog.business.comment.Comment;
import com.study.blog.business.tag.Tag;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@DynamicInsert
@DynamicUpdate
@Getter
public class Post extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(nullable = false)
    @Lob
    private String content;
    @Column(nullable = false)
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Post() { }

    public Post(Long id) {
        this.id = id;
    }

    public Post(Category category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

    @PreRemove
    private void preRemove() {
        for (Comment comment : comments) {
            comment.unlinkPost();
        }
    }

    public void updatePost(Category category, String title, String content ) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

    public void switchStatus() {
        this.status = !this.status;
    }

    public void unlinkCategory() {
        this.category = null;
    }

    public void updateTags(Set<Tag> tags) {
        this.tags = tags;
    }
}


