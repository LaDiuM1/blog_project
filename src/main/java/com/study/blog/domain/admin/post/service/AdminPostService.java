package com.study.blog.domain.admin.post.service;

import com.study.blog.domain.admin.post.request.CreatePostRequest;
import com.study.blog.infrastructure.persistence.entity.Post;
import com.study.blog.infrastructure.persistence.entity.Tag;
import com.study.blog.infrastructure.persistence.repository.post.PostRepository;
import com.study.blog.infrastructure.persistence.repository.tag.TagRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public void createPost(CreatePostRequest request){
        Set<Tag> tagSet = new HashSet<Tag>();

        if(Optional.ofNullable(request.getTagNameSet()).isPresent()){
            request.getTagNameSet().forEach( tagName ->{
                Optional<Tag> OptionalTag = tagRepository.findTagByName(tagName);
                if(OptionalTag.isPresent()){
                    tagSet.add(OptionalTag.get());
                }
                else{
                    tagSet.add(tagRepository.save(new Tag(tagName)));
                }
            });
        }
        Post post = new Post(request.getTitle(), request.getContent(), tagSet);

        postRepository.save(post);

    }

}
