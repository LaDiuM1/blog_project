package com.study.blog.domain.post;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.tag.repository.Tag;
import com.study.blog.domain.tag.repository.TagRepository;
import com.study.blog.domain.tag.response.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostTagUpdater {

    private final TagRepository tagRepository;

    public void postAddTags(Post post, Set<String> tagNames){
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

    public void matchPostAndTags(List<PostListResponse> postList){
        Set<Long> postIds = postList.stream().map(PostListResponse::getId).collect(Collectors.toSet());

        Map<Long, List<TagResponse>> postTagsMap = tagRepository.getPostIdAndTagMap(postIds);

        postList.forEach(postResponse -> {
            List<TagResponse> tags = postTagsMap.getOrDefault(postResponse.getId(), Collections.emptyList());
            postResponse.setTags(tags);
        });
    }

}
