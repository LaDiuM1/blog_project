package com.study.blog.infrastructure.persistence.repository.tag;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.admin.tag.response.SearchTagResponse;
import com.study.blog.infrastructure.persistence.entity.QTag;
import com.study.blog.infrastructure.persistence.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom{

    private final JPAQueryFactory query;
    private final EntityManager entityManager;

    public List<Tag> existingTagsByName(Set<String> tagNames) {
        QTag tag = QTag.tag;
        return query.select(tag)
                .from(tag)
                .where(tag.name.in(tagNames))
                .fetch();
    }

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

    public void saveTags(Set<Tag> tags){
        for (Tag tag : tags) {
            entityManager.persist(tag);
        }
    }


}
