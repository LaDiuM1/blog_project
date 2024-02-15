package com.study.blog.infrastructure.persistence.repository.tag;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.admin.tag.response.SearchTagResponse;
import com.study.blog.infrastructure.persistence.entity.QTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom{

    private final JPAQueryFactory query;

    public List<SearchTagResponse> searchTag(String name) {
        QTag tag = QTag.tag;

        return query.select(Projections.
                        constructor(SearchTagResponse.class, tag.name))
                .from(tag)
                .where(tag.name.containsIgnoreCase(name))
                .orderBy(tag.name.length().asc())
                .limit(10)
                .fetch();
    }


}
