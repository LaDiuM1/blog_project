package com.study.blog.domain.post;

import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.repository.PostRepository;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.PostListRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import com.study.blog.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostQuery postQuery;
    private final PostCreator postCreator;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;

    private final PostTagUpdater postTagUpdater;

    @Transactional
    public Long createPost(CreatePostRequest request){
        Post createdPost = postCreator.createPost(request);
        postTagUpdater.postAddTags(createdPost, request.getTagNames());
        return createdPost.getId();
    }

    public Page<PostListResponse> searchPostList(PostListRequest request, Pageable pageable){
        Page<PostListResponse> postList = postQuery.searchPostList(request, pageable);
        postTagUpdater.matchPostAndTags(postList.getContent());

        return postList;
    }

    public PostResponse getPost(Long id){
        return postQuery.getPost(id);
    }

    public void updatePost(Long postId, UpdatePostRequest request){
        postUpdater.updatePost(postId, request);
    }

    public void updatePostStatus(Long postId){
        postUpdater.updatePostStatus(postId);
    }

    public void deletePost(Long postId){
        postDeleter.deletePost(postId);
    }

}
