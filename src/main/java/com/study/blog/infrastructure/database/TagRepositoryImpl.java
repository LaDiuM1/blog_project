package com.study.blog.infrastructure.database;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.business.post.QPost;
import com.study.blog.business.tag.QTag;
import com.study.blog.business.tag.dto.SearchTagDto;
import com.study.blog.business.tag.dto.TagDto;
import com.study.blog.business.tag.repository.TagRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@Transactional
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom {

    private final JPAQueryFactory query;

    public List<SearchTagDto> searchTag(String name) {
        QTag tag = QTag.tag;

        return query.select(Projections.
                        constructor(SearchTagDto.class, tag.name))
                .from(tag)
                .where(tag.name.containsIgnoreCase(name))
                .orderBy(tag.name.length().asc())
                .limit(10)
                .fetch();
    }

    public Map<Long, List<TagDto>> getPostIdAndTagMap(Set<Long> postIds){
        QTag tag = QTag.tag;
        QPost post = QPost.post;

        return query
                .from(tag)
                .join(tag.posts, post)
                .where(post.id.in(postIds))
                .transform(GroupBy.groupBy(post.id).as(GroupBy.list(Projections.constructor(TagDto.class, tag.id, tag.name))));
                // post 기준으로 그룹핑하여 태그 집합 생성, GroupBy.list로 포스트 id에 대응하는 태그 집합 반환
    }

}
