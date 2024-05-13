package com.study.blog.business.comment;

import com.study.blog.business.comment.CommentRepository;
import com.study.blog.business.comment.CommentService;
import com.study.blog.business.comment.dto.CommentListDto;
import com.study.blog.presentation.controller.request.CommentListRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-comments-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CommentServiceTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("댓글 검색 호출 검증")
    void searchCommentList_success() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        String searchContent = "테스트";
        boolean searchStatus = true;

        CommentListRequest request = new CommentListRequest(searchContent, searchStatus);

        // when
        List<CommentListDto> verifyResponse = commentService.searchCommentList(request.toData(), pageable).getContent();

        // then
        assertThat(verifyResponse.size()).isNotZero();
        verifyResponse.forEach(verifyComment -> {
            assertThat(verifyComment.getCommentContent()).contains(request.getSearchContent());
            assertThat(verifyComment.isStatus()).isEqualTo(request.getSearchStatus());
        });
    }

    @Test
    @DisplayName("댓글 상태 변경 검증")
    void updateCommentStatus_existingComment_success() {
        // given
        long commentId = 1L;
        boolean beforeCommentStatus = commentRepository.findById(commentId).get().isStatus();

        // when
        commentService.switchCommentStatus(commentId);
        boolean afterCommentStatus = commentRepository.findById(commentId).get().isStatus();

        assertThat(afterCommentStatus).isNotEqualTo(beforeCommentStatus);
    }

    @Test
    @DisplayName("댓글 상태 변경, 존재하지 않는 id -> throw")
    void updateCommentStatus_nonExistingComment_throw() {
        // given
        long nonExistingCommentId = commentRepository.count()+1;

        // when, then
        assertThrows(EntityNotFoundException.class, () ->
            commentService.switchCommentStatus(nonExistingCommentId)
        );
    }

}