package com.study.blog.business.category;

import com.study.blog.business.BaseTime;
import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.post.Post;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@DynamicInsert
@DynamicUpdate
@Getter
public class Category extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, unique = true, nullable = false)
    private String name;
    @Column
    private String description;
    @Column(nullable = false)
    private boolean status = true;
    @Column(nullable = false)
    private int sequence;

    @OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Post> posts = new ArrayList<>();

    @PreRemove
    private void preRemove() {
        for (Post post : posts) {
            post.unlinkCategory();
        }
    }

    public Category() {}

    public Category(String name, String description, int sequence) {
        this.name = name;
        this.description = description;
        this.sequence = sequence;
    }


    public Category(Long id){
        this.id = id;
    }

    public CategoryDto toResponse() {
        return new CategoryDto(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.isStatus(),
                this.getSequence(),
                this.getCreateDate(),
                this.getUpdateDate()
        );
    }

    public void updateCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void switchStatus() {
        this.status = !this.status;
    }

    public LocalDateTime getUpdateDate(){
        return this.updateDate;
    }

    public LocalDateTime getCreateDate(){
        return this.createDate;
    }



}
