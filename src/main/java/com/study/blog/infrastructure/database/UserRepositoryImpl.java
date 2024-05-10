package com.study.blog.infrastructure.database;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.business.common.QUser;
import com.study.blog.presentation.controller.request.SearchAdminRequest;
import com.study.blog.business.admin.AdminListDto;
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
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory query;

    private BooleanBuilder searchAdminBooleanBuilder(QUser user, SearchAdminRequest request){
        BooleanBuilder builder = new BooleanBuilder();
        String searchEmail = request.getSearchEmail();
        String searchName = request.getSearchName();
        Boolean searchStatus = request.getSearchStatus();

        if (searchEmail != null && !searchEmail.trim().isEmpty()) {
            builder.and(user.email.containsIgnoreCase(searchEmail));
        }
        if (searchName != null && !searchName.trim().isEmpty()) {
            builder.and(user.name.containsIgnoreCase(searchName));
        }
        if (searchStatus != null) {
            builder.and(user.status.eq(searchStatus));
        }

        return builder;
    }

    public Page<AdminListDto> searchAdminList(SearchAdminRequest request, Pageable pageable) {
        QUser user = QUser.user;
        BooleanBuilder builder = searchAdminBooleanBuilder(user, request);

        List<AdminListDto> fetch = query.select(Projections.constructor(AdminListDto.class,
                        user.id,
                        user.email,
                        user.name,
                        user.status
                ))
                .from(user)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = query.select(user.count())
                .from(user)
                .where(builder);

        return PageableExecutionUtils.getPage(
                fetch,
                pageable,
                count::fetchOne
        );
    }

}
