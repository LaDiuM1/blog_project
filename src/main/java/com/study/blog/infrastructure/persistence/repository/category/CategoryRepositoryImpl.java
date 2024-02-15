package com.study.blog.infrastructure.persistence.repository.category;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.admin.category.response.CategoryListResponse;
import com.study.blog.infrastructure.persistence.entity.QCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory query;

    public Integer getCreateSequenceNumber(){
        QCategory category = QCategory.category;

        return query.select(category.sequence.max().add(1).coalesce(1))
                .from(category)
                .fetchOne();

    }

    public List<CategoryListResponse> getAdminCategoryList() {
        QCategory category = QCategory.category;

        return query.select(Projections.constructor(CategoryListResponse.class,
                        category.id,
                        category.name,
                        category.description,
                        category.status,
                        category.sequence))
                .from(category)
                .fetch();
    }

}
