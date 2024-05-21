package com.study.blog.business.post;

import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.business.post.repository.PostRepository;
import com.study.blog.business.tag.Tag;
import com.study.blog.presentation.controller.request.PostSearchRequest;
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

    public Page<PostListDto> searchPostList(PostSearchRequest request, Pageable pageable){
        return postRepository.searchPostList(request, pageable);
    }

    public PostDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String title = post.getTitle();
        String content = post.getContent();
        Set<String> tagNames = post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new PostDto(id, title, content, tagNames);
    }

}
