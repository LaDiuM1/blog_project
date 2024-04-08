package com.study.blog.domain.category.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.category.response.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Repository
@Transactional
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory query;

    public Category findByIdOrThrow(Long id) throws EntityNotFoundException {
        QCategory qCategory = QCategory.category;

        Category category = query.selectFrom(qCategory)
                .where(qCategory.id.eq(id))
                .fetchOne();

        if (category == null) { throw new EntityNotFoundException(); }

        return category;
    }

    private NumberExpression<Integer> sequenceCaseBuilder(QCategory category, Set<Long> idSet){
        CaseBuilder caseBuilder = new CaseBuilder();
        NumberExpression<Integer> categorySequence = category.sequence;
        int sequenceNumber = 1;
        for (Long id : idSet) {
            categorySequence = caseBuilder.when(category.id.eq(id)).then(sequenceNumber++).otherwise(categorySequence);
        }
        return categorySequence;
    }

    public Integer getCreateSequenceNumber(){
        QCategory category = QCategory.category;

        return query.select(category.sequence.max().add(1).nullif(1))
                .from(category)
                .fetchOne();
    }

    public List<CategoryListResponse> getCategoryList() {
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

    public boolean updateCategoryValid(Set<Long> idSet){
        QCategory category = QCategory.category;

        JPAQuery<Long> jpaQuery = query.select(category.count())
                .from(category);

        Long categoryCount = jpaQuery.fetchOne();
        Long requestCount = jpaQuery.where(category.id.in(idSet)).fetchOne();

        return !Objects.equals(requestCount, categoryCount);
    }

    public void updateCategorySequence(Set<Long> idSet){
        QCategory category = QCategory.category;
        NumberExpression<Integer> cases = sequenceCaseBuilder(category, idSet);

        query.update(category)
                .set(category.sequence, cases)
                .where(category.id.in(idSet))
                .execute();
    }
}
