package com.study.blog.infrastructure.persistence.repository.tag;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.admin.post.response.PostListResponse;
import com.study.blog.domain.admin.tag.response.SearchTagResponse;
import com.study.blog.domain.admin.tag.response.TagResponse;
import com.study.blog.infrastructure.persistence.entity.QPost;
import com.study.blog.infrastructure.persistence.entity.QTag;
import com.study.blog.infrastructure.persistence.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Map<Long, List<TagResponse>> getPostIdAndTagMap(Set<Long> postIds){
        QTag tag = QTag.tag;
        QPost post = QPost.post;

        return query
                .from(tag)
                .join(tag.posts, post)
                .where(post.id.in(postIds))
                .transform(GroupBy.groupBy(post.id).as(GroupBy.list(Projections.constructor(TagResponse.class, tag.id, tag.name))));
                // post 기준으로 그룹핑하여 태그 집합 생성, GroupBy.list로 포스트 id에 대응하는 태그 집합 반환
    }

}
