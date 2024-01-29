package com.study.blog.model.entity.category;

import com.study.blog.model.entity.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter @NoArgsConstructor
public class Category extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    private String description;

    private boolean state;

    private int sequence;
}
