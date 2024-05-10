package com.study.blog.infrastructure.database;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.business.comment.repository.QComment;
import com.study.blog.presentation.controller.request.CommentListRequest;
import com.study.blog.business.comment.CommentListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory query;

    private BooleanBuilder getCommentListBooleanBuilder(QComment comment, String searchContent, Boolean searchStatus){
        BooleanBuilder builder = new BooleanBuilder();

        if (searchContent != null && !searchContent.trim().isEmpty()) {
            builder.and(comment.commentContent.containsIgnoreCase(searchContent));
        }

        if (searchStatus != null) {
            builder.and(comment.status.eq(searchStatus));
        }

        return builder;
    }

    public Page<CommentListDto> searchCommentList(CommentListRequest request, Pageable pageable) {
        QComment comment = QComment.comment;
        QComment commentSub = new QComment("commentSub");

        BooleanBuilder builder = getCommentListBooleanBuilder(comment, request.getSearchContent(), request.getSearchStatus());

        List<CommentListDto> fetch = query.select(Projections.constructor(CommentListDto.class,
                        comment.id,
                        comment.parentCommentId,
                        comment.commentContent,
                        comment.commentAuthorName,
                        comment.commentAuthorEmail,
                        comment.status,
                        comment.post.id,
                        comment.post.title,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(commentSub.commentContent)
                                        .from(commentSub)
                                        .where(commentSub.id.eq(comment.parentCommentId)),
                                "parentCommentContent")
                ))
                .from(comment)
                .leftJoin(comment.post) // post가 null일 경우 검색하기 위한 left outer join
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = query.select(comment.count())
                .from(comment)
                .where(builder);

        return PageableExecutionUtils.getPage(
                fetch,
                pageable,
                count::fetchOne
        );


    }

}
