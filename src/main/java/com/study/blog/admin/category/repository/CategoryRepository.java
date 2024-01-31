package com.study.blog.admin.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.infrastructure.entity.category.QCategory;
import com.study.blog.infrastructure.persistence.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

}
