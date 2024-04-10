package com.study.blog.domain.post.service;

import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.tag.repository.Tag;
import com.study.blog.domain.tag.repository.TagRepository;
import com.study.blog.domain.tag.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class UpdatePostTag {

    private final TagRepository tagRepository;

    void postAddTags(Post post, Set<String> tagNames){
        List<Tag> existingTags = tagRepository.findTagsByNameIn(tagNames);
        Set<Tag> tags = new HashSet<>();

        Map<String, Tag> existingTagsMap = existingTags.stream()
                .collect(Collectors.toMap(Tag::getName, Function.identity()));

        Set<Tag> newTags = new HashSet<>();
        for (String tagName : tagNames) {
            if (existingTagsMap.containsKey(tagName)) {
                tags.add(existingTagsMap.get(tagName));
            }
            else {
                newTags.add(new Tag(tagName));
            }
        }
        tags.addAll(tagRepository.saveAll(newTags));
        post.updateTags(tags);
    }

    void matchPostAndTags(List<PostListResponse> postList){
        Set<Long> postIds = postList.stream().map(PostListResponse::getId).collect(Collectors.toSet());

        Map<Long, List<TagResponse>> postTagsMap = tagRepository.getPostIdAndTagMap(postIds);

        postList.forEach(postResponse -> {
            List<TagResponse> tags = postTagsMap.getOrDefault(postResponse.getId(), Collections.emptyList());
            postResponse.setTags(tags);
        });
    }

}
