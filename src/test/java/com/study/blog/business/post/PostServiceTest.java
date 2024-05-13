package com.study.blog.business.post;

import com.study.blog.business.post.Post;
import com.study.blog.business.post.PostRepository;
import com.study.blog.business.post.PostService;
import com.study.blog.business.post.data.PostData;
import com.study.blog.business.tag.Tag;
import com.study.blog.business.category.CategoryRepository;
import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.business.post.dto.PostDto;
import com.study.blog.presentation.controller.request.CreatePostRequest;
import com.study.blog.presentation.controller.request.SearchPostRequest;
import com.study.blog.presentation.controller.request.UpdatePostRequest;
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

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-categories-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-posts-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시글 생성 서비스 검증")
    @Transactional
    public void createPost_success() {
        // given
        Long categoryId = 1L;
        String postTitle = "게시글 생성 테스트 제목";
        String postContent = "게시글 생성 테스트 내용";
        String tagName1 = "게시글 생성 테스트 태그1";
        String tagName2 = "게시글 생성 테스트 태그2";
        HashSet<String> tagSet = new HashSet<>(List.of(tagName1, tagName2));

        PostData postData = new CreatePostRequest(categoryId, postTitle, postContent, tagSet).toData();

        // when
        postService.createPost(postData);

        // then
        Post verifyPost = postRepository.findById(postRepository.count()).get();

        assertThat(verifyPost.getTitle()).isEqualTo(postData.getTitle());
        assertThat(verifyPost.getContent()).isEqualTo(postData.getContent());
        assertThat(verifyPost.isStatus()).isTrue();
        assertThat(verifyPost.getCategory().getId()).isEqualTo(postData.getCategoryId());

        assertThat(verifyPost.getTags().size()).isEqualTo(tagSet.size());
        boolean verifyTagName1 = verifyPost.getTags().stream().anyMatch(tag -> tag.getName().equals(tagName1));
        boolean verifyTagName2 = verifyPost.getTags().stream().anyMatch(tag -> tag.getName().equals(tagName2));

        assertThat(verifyTagName1).isTrue();
        assertThat(verifyTagName2).isTrue();
    }

    @Test
    @DisplayName("게시글 생성 서비스, 존재하지 않는 카테고리 id -> throw")
    @Transactional
    public void createPost_throw() {
        // given
        long notExistingCategoryId = categoryRepository.count()+1;
        PostData postData = new CreatePostRequest(notExistingCategoryId, new String(), new String(), new HashSet<String>()).toData();

        // when, then
        assertThatThrownBy(() -> postService.createPost(postData))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("게시글 검색 서비스, 검색 항목 일치 확인")
    @Transactional
    public void searchPost_success() {
        // given
        long searchCategoryId = 1L;
        String searchTitle = "테스트 제목";
        String searchContent = "테스트 내용";
        boolean searchStatus = true;

        Pageable pageable = PageRequest.of(0, 10);
        SearchPostRequest request = new SearchPostRequest(searchCategoryId, searchTitle, searchContent, searchStatus);

        // when
        Page<PostListDto> searchPostList = postService.searchPostList(request, pageable);

        // then
        List<PostListDto> verifyPostResponseList = searchPostList.getContent();
        String verifyCategoryName = categoryRepository.findById(request.getSearchCategoryId()).get().getName();

        assertThat(verifyPostResponseList.size()).isNotZero();
        assertThat(verifyPostResponseList.stream().anyMatch(post -> post.getTitle().contains(request.getSearchTitle()))).isTrue();
        assertThat(verifyPostResponseList.stream().anyMatch(post -> post.getContent().contains(request.getSearchContent()))).isTrue();
        assertThat(verifyPostResponseList.stream().anyMatch(post -> post.getCategoryName().contains(verifyCategoryName))).isTrue();
        assertThat(verifyPostResponseList.stream().anyMatch(PostListDto::getStatus)).isTrue();
    }

    @Test
    @DisplayName("개별 게시글 조회 서비스, 일치 여부 확인")
    @Transactional
    public void getPost_success() {
        // given
        Long id = 1L;

        // when
        PostDto postDto = postService.getPost(id);

        // then
        assertThat(postDto.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("게시글 조회 서비스, 존재하지 않는 id -> throw")
    @Transactional
    public void getPost_throw() {
        // given
        long notExistingPostId = postRepository.count()+1;

        // when, then
        assertThatThrownBy(() -> postService.getPost(notExistingPostId))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("게시글 업데이트 서비스, 매개변수 값 일치 확인")
    @Transactional
    public void updatePost_success() {
        // given
        Long requestPostId = 1L;
        Long updateCategoryId = 2L;
        String updateTitle = "변경 후 제목";
        String updateContent = "변경 후 내용";
        HashSet<String> updateTagSet = new HashSet<>(Set.of("변경 태그1", "변경 태그2", "변경 태그3"));

        UpdatePostRequest request = new UpdatePostRequest(updateCategoryId, updateTitle, updateContent, updateTagSet);

        // when
        postService.updatePost(requestPostId, request);

        // then
        Post verifyPost = postRepository.findById(requestPostId).get();

        assertThat(verifyPost.getTitle()).isEqualTo(request.getTitle());
        assertThat(verifyPost.getContent()).isEqualTo(request.getContent());
        assertThat(verifyPost.getCategory().getId()).isEqualTo(request.getCategoryId());
        assertThat(verifyPost.getTags().size()).isNotZero();
        updateTagSet.forEach(tagName ->
            assertThat(verifyPost.getTags().stream().map(Tag::getName).collect(Collectors.toSet()).contains(tagName)).isTrue()
        );
    }
    @Test
    @DisplayName("게시글 업데이트 서비스, 존재하지 않는 게시글 id -> throw")
    public void updatePost_notExistingPostId_throw() {
        // given
        long notExistingPostId = postRepository.count()+1;
        long existingCategoryId = 1L;

        UpdatePostRequest request = new UpdatePostRequest(existingCategoryId, new String(), new String(), new HashSet<String>());

        // when, then
        assertThatThrownBy(() -> postService.updatePost(notExistingPostId, request))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("게시글 업데이트 서비스, 존재하지 않는 카테고리 id -> throw")
    public void updatePost_notExistingCategoryId_throw() {
        // given
        long existingPostId = 1L;
        long notExistingCategoryId = postRepository.count()+1;

        UpdatePostRequest request = new UpdatePostRequest(notExistingCategoryId, new String(), new String(), new HashSet<String>());

        // when, then
        assertThatThrownBy(() -> postService.updatePost(existingPostId, request))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("게시글 상태 업데이트 서비스 검증")
    public void updatePostStatus_success() {
        // given
        Long id = 1L;
        boolean beforeUpdateStatus = postRepository.findByIdOrThrow(id).isStatus();

        // when
        postService.updatePostStatus(id);
        boolean afterUpdateStatus = postRepository.findByIdOrThrow(id).isStatus();

        // then
        assertThat(beforeUpdateStatus).isNotEqualTo(afterUpdateStatus);
    }

    @Test
    @DisplayName("게시글 상태 업데이트 서비스, 존재하지 않는 id -> throw")
    public void updatePostStatus_throw() {
        // given
        long notExistingPostId = postRepository.count()+1;

        // when, then
        assertThatThrownBy(() -> postService.updatePostStatus(notExistingPostId))
                .isInstanceOf(EntityNotFoundException.class);
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
        assertThat(beforeDeletePost).isTrue();
        assertThat(afterDeletePost).isFalse();
    }

    @Test
    @DisplayName("게시글 삭제 서비스, 존재하지 않는 id -> throw")
    public void deletePost_throw() {
        // given
        long notExistingPostId = postRepository.count()+1;

        // when, then
        assertThatThrownBy(() -> postService.deletePost(notExistingPostId))
                .isInstanceOf(EntityNotFoundException.class);
    }


}