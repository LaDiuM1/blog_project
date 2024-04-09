package com.study.blog.domain.post.service;

import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.tag.repository.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReadPost {

    private final PostRepository postRepository;

    public Page<PostListResponse> searchPostList(PostListRequest request, Pageable pageable){
        return postRepository.searchPost(request, pageable);
    }

    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String title = post.getTitle();
        String content = post.getContent();
        Set<String> tagNames = post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new PostResponse(id, title, content, tagNames);
    }

}
