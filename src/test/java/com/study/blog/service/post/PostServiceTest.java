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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-category-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-post-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class PostServiceTest extends PostSupportService {

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
        Long categoryId = 1L;
        String postTitle = "테스트 제목";
        String postContent = "테스트 내용";
        HashSet<String> tagSet = new HashSet<>(List.of("태그1", "태그2"));

        CreatePostRequest request = new CreatePostRequest(categoryId, postTitle, postContent, tagSet);

        // when
        postService.createPost(request);

        // then
        Post verifyPost = postRepository.findByIdOrThrow(6L);

        assertThat(verifyPost.getTitle()).isEqualTo(request.getTitle());
        assertThat(verifyPost.getContent()).isEqualTo(request.getContent());
        assertThat(verifyPost.isStatus()).isTrue();
        assertThat(verifyPost.getCategory().getId()).isEqualTo(request.getCategoryId());

        assertThat(verifyPost.getTags().size()).isEqualTo(2);
        boolean verifyTagName1 = verifyPost.getTags().stream().anyMatch(tag -> tag.getName().equals("태그1"));
        boolean verifyTagName2 = verifyPost.getTags().stream().anyMatch(tag -> tag.getName().equals("태그2"));

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
        });
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
        assertThat(postResponse.getId()).isEqualTo(id);
    }

    @Test
    @Sql(value = "/sql/test-category-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    // 클래스 레벨로 선언된 post insert를 사용하지 않도록 필요 sql로만 재정의
    @DisplayName("게시글 업데이트 서비스, 변경 여부 확인")
    @Transactional
    public void updatePost_success() {
        // given
        Long requestUpdatePostId = 1L;
        Long beforeCategoryId = 1L;
        String beforeTitle = "변경 전 제목";
        String beforeContent = "변경 전 제목";
        HashSet<Tag> beforeTagSet = new HashSet<>(List.of(
                new Tag("beforeTag1"),new Tag("beforeTag2"), new Tag("beforeTag3")));

        Post beforePost = new Post();
        beforePost.setId(requestUpdatePostId);
        beforePost.setTitle(beforeTitle);
        beforePost.setContent(beforeContent);
        beforePost.setTags(beforeTagSet);
        beforePost.setCategory(new Category(beforeCategoryId));
        postRepository.save(beforePost);

        Long afterCategoryId = 2L;
        String afterTitle = "변경 후 제목";
        String afterContent = "변경 후 제목";
        HashSet<String> afterTagSet = new HashSet<>(List.of("afterTag1", "afterTag2", "afterTag3"));

        UpdatePostRequest request = new UpdatePostRequest(
                requestUpdatePostId, afterCategoryId, afterTitle, afterContent, afterTagSet);

        // when
        postService.updatePost(request);

        Post updatedPost = postRepository.findByIdOrThrow(requestUpdatePostId);

        // then
        assertThat(updatedPost.getId()).isEqualTo(requestUpdatePostId);
        assertThat(updatedPost.getTitle()).isEqualTo(afterTitle);
        assertThat(updatedPost.getContent()).isEqualTo(afterContent);
        assertThat(updatedPost.getCategory().getId()).isEqualTo(afterCategoryId);
        updatedPost.getTags().forEach(tag ->
            assertThat(afterTagSet.contains(tag.getName())).isTrue()
        );
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