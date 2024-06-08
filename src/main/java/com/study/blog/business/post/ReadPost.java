package com.study.blog.business.post;

import com.study.blog.business.post.data.PostSearchData;
import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.business.post.repository.PostRepository;
import com.study.blog.business.tag.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class ReadPost {

    private final PostRepository postRepository;

    public Page<PostListDto> searchPostList(PostSearchData searchData, Pageable pageable){
        return postRepository.searchPostList(searchData, pageable);
    }

    public PostDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String title = post.getTitle();
        String content = post.getContent();
        Set<String> tagNames = post.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

        return new PostDto(id, title, content, tagNames);
    }

}
