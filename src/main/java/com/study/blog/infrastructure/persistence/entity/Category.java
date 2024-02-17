package com.study.blog.infrastructure.persistence.entity;

import com.study.blog.domain.admin.category.response.CategoryResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public Category(String name, String description, int sequence) {
        this.name = name;
        this.description = description;
        this.sequence = sequence;
    }

    public CategoryResponse toDto() {
        return new CategoryResponse(
                this.getId(),
                this.getName(),
                this.getDescription(),
                this.isStatus(),
                this.getSequence(),
                this.getCreateDate(),
                this.getUpdateDate()
        );
    }

    public LocalDateTime getUpdateDate(){
        return this.updateDate;
    }

    public LocalDateTime getCreateDate(){
        return this.createDate;
    }



}
