package com.study.blog.service.comment;

import com.study.blog.infrastructure.persistence.entity.Comment;
import com.study.blog.infrastructure.persistence.repository.comment.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @InjectMocks    // commentService의 인스턴스를 생성, Mock의 모의 객체 주입
    private CommentService commentService;

    @Captor // Comment의 상태 포착을 위한 기능
    private ArgumentCaptor<Comment> mockCommentCaptor;

    @BeforeEach
    void setUp() {
        // 테스트 전 모의 객체 초기화
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void updateCommentStatus_existingComment_success() {
        long oldCommentId = 1L;
        boolean oldCommentStatus = true;

        // 테스트 comment 객체 생성
        Comment mockComment = new Comment();
        mockComment.setId(oldCommentId);
        mockComment.setStatus(oldCommentStatus); // 초기 상태 값 true

        // 메서드 호출 시 반환 객체를 위 comment 객체로 지정
        when(commentRepository.findById(oldCommentId)).thenReturn(Optional.of(mockComment));

        // result
        commentService.updateCommentStatus(oldCommentId);
        assertNotEquals(mockComment.isStatus(), oldCommentStatus);
    }

    @Test
    void updateCommentStatus_nonExistingComment_fail() {
        // 존재하지 않는 id를 위한 설정, 객체 생성이 없으므로 해당 객체는 없음을 가정
        long nonExistingCommentId = 1L;

        when(commentRepository.findById(nonExistingCommentId)).thenReturn(Optional.empty());

        // 상태 업데이트 메서드 호출 시 예외 발생 여부 검증
        assertThrows(EntityNotFoundException.class, () -> {
            commentService.updateCommentStatus(nonExistingCommentId);
        });
    }

}