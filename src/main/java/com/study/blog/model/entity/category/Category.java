package com.study.blog.model.entity.category;

import com.study.blog.model.entity.BaseTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor
public class Category extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;
    @Column
    private String description;
    @Column
    private boolean state = true;
    @Column
    private int sequence;

    public Category(String name, String description, int sequence) {
        this.name = name;
        this.description = description;
        this.sequence = sequence;
    }
}
