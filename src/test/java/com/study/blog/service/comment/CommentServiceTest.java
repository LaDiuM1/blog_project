package com.study.blog.service.comment;

import com.study.blog.infrastructure.persistence.entity.Comment;
import com.study.blog.infrastructure.persistence.repository.comment.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void updateCommentStatus_existingComment_success() {
        // 테스트 comment 객체 생성
        Comment mockComment = new Comment();
        mockComment.setId(1L);
        mockComment.setStatus(true); // 초기 상태 값 true
        // 메서드 호출 시 반환 객체를 위 comment 객체로 지정
        when(commentRepository.findById(1L)).thenReturn(Optional.of(mockComment));

        // 상태 업데이트 메서드 호출
        commentService.updateCommentStatus(1L);

        // 세이브 시도 시 전달 객체의 상태를 ArgumentCaptor에 캡처
        verify(commentRepository).save(mockCommentCaptor.capture());
        // ArgumentCaptor의 캡처 값 호출
        Comment updatedComment = mockCommentCaptor.getValue();
        // 초기 상태가 true, 상태가 false로 변경되었는지 검증
        assertFalse(updatedComment.isStatus());
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

        // save 메서드가 호출되지 않았는지 확인, never는 호출이 없어야 함을 설정
        verify(commentRepository, never()).save(any(Comment.class));
    }

}