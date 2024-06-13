package com.study.blog.infrastructure.database;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.business.comment.QComment;
import com.study.blog.business.post.Post;
import com.study.blog.business.post.QPost;
import com.study.blog.business.post.data.PostSearchData;
import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.business.post.dto.QPostDto;
import com.study.blog.business.post.repository.PostRepositoryCustom;
import com.study.blog.business.tag.QTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory query;

    private BooleanBuilder searchPostBooleanBuilder(QPost post, PostSearchData searchData){
        BooleanBuilder builder = new BooleanBuilder();
        Long searchCategoryId = searchData.getSearchCategoryId();
        String searchTitle = searchData.getSearchTitle();
        String searchContent = searchData.getSearchContent();
        Boolean searchStatus = searchData.getSearchStatus();

        if (searchCategoryId != null) {
            builder.and(post.category.id.eq(searchCategoryId));
        }

        if (searchTitle != null && !searchTitle.trim().isEmpty()) {
            builder.and(post.title.containsIgnoreCase(searchTitle));
        }

        if (searchContent != null && !searchContent.trim().isEmpty()) {
            builder.and(post.content.containsIgnoreCase(searchContent));
        }

        if (searchStatus != null) {
            builder.and(post.status.eq(searchStatus));
        }

        return builder;
    }

    @Override
    public Page<PostListDto> searchPostList(PostSearchData searchData, Pageable pageable) {
        QPost post = QPost.post;
        QComment comment = QComment.comment;

        BooleanBuilder builder = searchPostBooleanBuilder(post, searchData);

        List<PostListDto> fetch = query.select(Projections.constructor(PostListDto.class,
                        post.id,
                        post.title,
                        post.content,
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

    @Override
    public Set<PostDto> findPosts(Set<Long> postIds) {
        QPost post = QPost.post;
        QTag tag = QTag.tag;

        Map<Long, PostDto> postDtoMap = query.from(post)
                .leftJoin(post.tags, tag)
                .where(post.id.in(postIds))
                .transform(GroupBy.groupBy(post.id).as(
                        new QPostDto(
                                post.id,
                                post.title,
                                post.content,
                                GroupBy.set(tag.name)
                        )
                ));

        return new HashSet<>(postDtoMap.values());
    }


}
