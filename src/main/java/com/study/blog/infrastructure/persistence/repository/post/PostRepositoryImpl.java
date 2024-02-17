package com.study.blog.infrastructure.persistence.repository.post;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.admin.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.entity.QComment;
import com.study.blog.infrastructure.persistence.entity.QPost;
import com.study.blog.infrastructure.persistence.entity.QTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory query;

    private BooleanBuilder getPostListBooleanBuilder(QPost post, Set<Long> searchCategoryIds, String searchKeyword, Boolean searchStatus){
        BooleanBuilder builder = new BooleanBuilder();

        if (searchCategoryIds != null && !searchCategoryIds.isEmpty()) {
            builder.and(post.category.id.in(searchCategoryIds));
        }

        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            builder.and(post.title.containsIgnoreCase(searchKeyword)
                    .or(post.content.containsIgnoreCase(searchKeyword)));
        }

        if (searchStatus != null) {
            builder.and(post.status.eq(searchStatus));
        }

        return builder;
    }

    public Page<PostListResponse> getPostList(Set<Long> searchCategoryIds, String searchKeyword, Boolean searchStatus, Pageable pageable) {
        QPost post = QPost.post;
        QComment comment = QComment.comment;
        QTag tag = QTag.tag;

        BooleanBuilder builder = getPostListBooleanBuilder(post, searchCategoryIds, searchKeyword, searchStatus);

        List<PostListResponse> fetch = query.select(Projections.constructor(PostListResponse.class,
                        post.id,
                        post.title,
                        post.status,
                        post.category.name,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(comment.count())
                                        .from(comment)
                                        .where(comment.post.id.eq(post.id)),
                                "commentCount")
                ))
                .from(post)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        fetch.forEach(postResponse -> {
            Set<String> tags = new HashSet<>(query.select(tag.name)
                    .from(tag)
                    .where(tag.posts.any().id.eq(postResponse.getId()))
                    .fetch());
            postResponse.setTags(tags);
        });

        JPAQuery<Long> count = query.select(post.count())
                .from(post)
                .where(builder);

        return PageableExecutionUtils.getPage(
                fetch,
                pageable,
                count::fetchOne
        );


    }

}
