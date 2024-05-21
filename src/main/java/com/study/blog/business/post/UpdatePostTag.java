package com.study.blog.business.post;

import com.study.blog.business.post.dto.PostListDto;
import com.study.blog.business.tag.Tag;
import com.study.blog.business.tag.dto.TagDto;
import com.study.blog.business.tag.repository.TagRepository;
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

    void matchPostAndTags(List<PostListDto> postList){
        Set<Long> postIds = postList.stream().map(PostListDto::getId).collect(Collectors.toSet());

        Map<Long, List<TagDto>> postTagsMap = tagRepository.getPostIdAndTagMap(postIds);

        postList.forEach(postResponse -> {
            List<TagDto> tags = postTagsMap.getOrDefault(postResponse.getId(), Collections.emptyList());
            postResponse.setTags(tags);
        });
    }

}
