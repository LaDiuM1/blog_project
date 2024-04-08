package com.study.blog.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.domain.comment.repository.QComment;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.response.PostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory query;

    public Post findByIdOrThrow(Long id) {
        QPost qPost = QPost.post;

        Post post = query.selectFrom(qPost)
                .where(qPost.id.eq(id))
                .fetchOne();

        if (post == null) { throw new EntityNotFoundException(); }

        return post;
    }

    private BooleanBuilder getPostListBooleanBuilder(QPost post, Long searchCategoryId, String searchKeyword, Boolean searchStatus){
        BooleanBuilder builder = new BooleanBuilder();

        if (searchCategoryId != null) {
            builder.and(post.category.id.eq(searchCategoryId));
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

    public Page<PostListResponse> searchPost(PostListRequest request, Pageable pageable) {
        Long searchCategoryId = request.getSearchCategoryId();
        String searchKeyword = request.getSearchKeyword();
        Boolean searchStatus = request.getSearchStatus();

        QPost post = QPost.post;
        QComment comment = QComment.comment;

        BooleanBuilder builder = getPostListBooleanBuilder(post, searchCategoryId, searchKeyword, searchStatus);

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
