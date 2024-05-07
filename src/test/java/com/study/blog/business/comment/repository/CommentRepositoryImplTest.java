package com.study.blog.business.comment.repository;

import com.study.blog.business.comment.CommentRepository;
import com.study.blog.presentation.controller.request.CommentListRequest;
import com.study.blog.presentation.controller.response.CommentListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-comments-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CommentRepositoryImplTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("keyword = '테스트', status = true : 댓글 검색")
    void getCommentList_validKeyword_success() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        String searchContent = "테스트";
        Boolean searchStatus = true;

        CommentListRequest request = new CommentListRequest(searchContent, searchStatus);

        // when
        Page<CommentListResponse> searchCommentList = commentRepository.searchCommentList(request, pageable);

        // then
        searchCommentList.getContent().forEach( comment -> {
            assertThat(comment.getCommentContent()).contains("테스트");
            assertThat(comment.isStatus()).isEqualTo(true);
        });
    }

    @Test
    @DisplayName("keyword = 'null', status = null : 댓글 검색")
    void getCommentList_keywordsIsNull_success() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        int commentCount = (int) commentRepository.count();

        CommentListRequest request = new CommentListRequest(null, null);

        // when
        Page<CommentListResponse> searchCommentList = commentRepository.searchCommentList(request, pageable);

        // then
        assertThat(searchCommentList.getContent().size()).isEqualTo(commentCount);
    }


}