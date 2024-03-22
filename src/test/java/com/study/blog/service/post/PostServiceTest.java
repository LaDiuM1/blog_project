package com.study.blog.service.post;

import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.infrastructure.persistence.repository.post.PostRepository;
import com.study.blog.infrastructure.persistence.repository.post.response.PostListResponse;
import com.study.blog.infrastructure.persistence.repository.post.response.PostResponse;
import com.study.blog.service.post.request.CreatePostRequest;
import com.study.blog.service.post.request.PostListRequest;
import com.study.blog.service.post.request.UpdatePostRequest;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-category-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-post-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
}) // SqlGroup을 특정 configuration으로 지정하여 원하는 메서드에만 사용하는 방법?
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글 생성 서비스 검증")
    @Transactional // post의 tag가 Lazy로 설정되어있어 세션 연결을 위해 Transactional 선언
    public void createPost_success() {
        // given
        CreatePostRequest request = new CreatePostRequest(
                1L, "테스트 제목", "테스트 내용", new HashSet<>(List.of("태그1", "태그2")));
        // when
        postService.createPost(request);

        // then
        Post verifyPost = postRepository.findByIdOrThrow(6L);
        Category verifyCategory = categoryRepository.findById(1L).get();

        assertThat(verifyPost.getTitle()).isEqualTo("테스트 제목");
        assertThat(verifyPost.getContent()).isEqualTo("테스트 내용");
        assertThat(verifyPost.isStatus()).isTrue();
        assertThat(verifyPost.getCategory().getId()).isEqualTo(verifyCategory.getId());

        assertThat(verifyPost.getTags().size()).isEqualTo(2);
        boolean verifyTagName1 = verifyPost.getTags().stream().anyMatch( tag -> tag.getName().equals("태그1"));
        boolean verifyTagName2 = verifyPost.getTags().stream().anyMatch( tag -> tag.getName().equals("태그2"));

        assertThat(verifyTagName1).isTrue();
        assertThat(verifyTagName2).isTrue();
    }

    @Test
    @DisplayName("게시글 검색 서비스, 검색 항목 일치 확인")
    public void searchPost_success() {
        // given
        Pageable pageable = PageRequest.of(0, 10);
        PostListRequest request = new PostListRequest();
        request.setSearchStatus(true);
        request.setSearchKeyword("테스트");
        request.setSearchCategoryId(1L);

        // when
        Page<PostListResponse> searchPostList = postService.searchPostList(request, pageable);

        // then
        assertThat(searchPostList.getContent().size()).isNotZero();
        searchPostList.getContent().forEach( post -> {
            assertThat(post.getTitle()).contains("테스트");
            assertThat(post.getCategoryName()).isEqualTo("카테고리 이름1"); // 사전 입력된 id 1의 카테고리 이름
            assertThat(post.getStatus()).isTrue(); // 상기 조건 검색 시 3개의 항목 검색, 여기서 status가 true인 2개 검증
        } );
    }

    @Test
    @DisplayName("게시글 조회 서비스, 일치 여부 확인")
    @Transactional
    public void getPost_success() {
        // given
        Long id = 1L;

        // when
        PostResponse postResponse = postService.getPost(id);

        // then
        assertThat(postResponse.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("게시글 업데이트 서비스, 변경 여부 확인")
    @Transactional
    public void updatePost_success() {
        // given
        Long id = 1L;
        UpdatePostRequest request = new UpdatePostRequest(
                id, 2L, "변경 후 제목", "변경 후 내용", new HashSet<>(List.of("태그2")));

        // when
        postService.updatePost(request);
        Post verifyPost = postRepository.findByIdOrThrow(id);

        // then
        assertThat(verifyPost.getTitle()).isEqualTo("변경 후 제목");
        assertThat(verifyPost.getContent()).isEqualTo("변경 후 내용");
        assertThat(verifyPost.getCategory().getId()).isEqualTo(2L);
        boolean verifyTagName = verifyPost.getTags().stream().allMatch( tag -> tag.getName().equals("태그2"));
        assertThat(verifyTagName).isTrue();
    }

    @Test
    @DisplayName("게시글 상태 업데이트 서비스 검증")
    public void updatePostStatus_success() {
        // given
        Long id = 1L;
        boolean beforeStatus = postRepository.findByIdOrThrow(id).isStatus();

        // when
        postService.updatePostStatus(id);
        boolean afterStatus = postRepository.findByIdOrThrow(id).isStatus();

        // then
        assertThat(beforeStatus).isNotEqualTo(afterStatus);
    }

    @Test
    @DisplayName("게시글 삭제 서비스 검증")
    public void deletePost_success() {
        // given
        Long id = 1L;
        boolean beforeDeletePost = postRepository.findById(id).isPresent();

        // when
        postService.deletePost(id);
        boolean afterDeletePost = postRepository.findById(id).isPresent();

        // then
        assertThat(beforeDeletePost).isNotEqualTo(afterDeletePost);
    }


}