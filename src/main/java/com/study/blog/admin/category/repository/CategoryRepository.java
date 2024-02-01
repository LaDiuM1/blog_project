package com.study.blog.admin.category.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.infrastructure.dto.category.CategoryDto;
import com.study.blog.infrastructure.entity.category.Category;
import com.study.blog.infrastructure.entity.category.QCategory;
import com.study.blog.infrastructure.persistence.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final JPAQueryFactory query;

    public int getCreateSequenceNumber (){
        QCategory category = QCategory.category;

        return query.select(category.sequence.max().add(1).coalesce(1))
                .from(category)
                .fetchOne();

    }

    public List<CategoryDto> getAdminCategoryList() {
        QCategory category = QCategory.category;

        return query.select(Projections.constructor(CategoryDto.class,
                        category.id,
                        category.name,
                        category.description,
                        category.state,
                        category.sequence))
                .from(category)
                .fetch();
    }

}
