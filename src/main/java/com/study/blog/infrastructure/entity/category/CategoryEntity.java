package com.study.blog.infrastructure.entity.category;

import com.study.blog.infrastructure.dto.category.CategoryDto;
import com.study.blog.infrastructure.entity.BaseTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryEntity extends BaseTime {

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

    public CategoryEntity(String name, String description, int sequence) {
        this.name = name;
        this.description = description;
        this.sequence = sequence;
    }

    public CategoryDto toDto() {
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

    public LocalDateTime getUpdateDate(){
        return this.updateDate;
    }

    public LocalDateTime getCreateDate(){
        return this.createDate;
    }




}
