package com.study.blog.domain.admin.post.service;

import com.study.blog.domain.admin.post.request.CreatePostRequest;
import com.study.blog.domain.admin.post.request.PostListRequest;
import com.study.blog.domain.admin.post.request.UpdatePostRequest;
import com.study.blog.domain.admin.post.response.PostListResponse;
import com.study.blog.domain.admin.post.response.PostResponse;
import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.infrastructure.persistence.repository.post.PostRepository;
import com.study.blog.infrastructure.persistence.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminPostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void updatePostTags(Post post, HashSet<String> tagNames){
        tagNames.forEach(tagName -> {
            Tag tag = tagRepository.findTagByName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(tagName)));
            post.getTags().add(tag);
        });
    }

    @Transactional
    public void createPost(CreatePostRequest request){
        Category category = categoryRepository.findByIdOrThrow(request.getCategoryId());

        Post post = new Post(category, request.getTitle(), request.getContent());

        if(Optional.ofNullable(request.getTagNames()).isPresent()){
            updatePostTags(post, request.getTagNames());
        }
        postRepository.save(post);

    }

    public Page<PostListResponse> getPostList(PostListRequest request, Pageable pageable){

        Set<Long> searchCategoryIds = request.getSearchCategoryIds();
        String searchKeyword = request.getSearchKeyword();
        Boolean searchStatus = request.getSearchStatus();

        return postRepository.getPostList(searchCategoryIds, searchKeyword, searchStatus, pageable);
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findByIdOrThrow(id);
        String title = post.getTitle();
        String content = post.getContent();
        Set<String> tagNames = post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new PostResponse(id, title, content, tagNames);
    }

    @Transactional
    public void updatePost(UpdatePostRequest request){
        Post post = postRepository.findByIdOrThrow(request.getId());
        Category category = categoryRepository.findByIdOrThrow(request.getCategoryId());

        post.setCategory(category);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        updatePostTags(post, request.getTagNames());

        postRepository.save(post);
    }

    @Transactional
    public void updatePostStatus(Long id){
        Post post = postRepository.findByIdOrThrow(id);
        post.setStatus(!post.isStatus());

        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findByIdOrThrow(id);
        postRepository.delete(post);
    }

}
