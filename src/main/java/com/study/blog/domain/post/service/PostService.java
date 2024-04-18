package com.study.blog.domain.post.service;

import com.study.blog.domain.post.repository.Post;
import com.study.blog.domain.post.request.CreatePostRequest;
import com.study.blog.domain.post.request.SearchPostRequest;
import com.study.blog.domain.post.request.UpdatePostRequest;
import com.study.blog.domain.post.response.PostListResponse;
import com.study.blog.domain.post.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ReadPost readPost;
    private final CreatePost createPost;
    private final UpdatePost updatePost;
    private final DeletePost deletePost;

    private final UpdatePostTag updatePostTag;

    @Transactional
    public Long createPost(CreatePostRequest request){
        Post createdPost = createPost.createPost(request);
        updatePostTag.postAddTags(createdPost, request.getTagNames());
        return createdPost.getId();
    }

    public Page<PostListResponse> searchPostList(SearchPostRequest request, Pageable pageable){
        Page<PostListResponse> postList = readPost.searchPostList(request, pageable);
        updatePostTag.matchPostAndTags(postList.getContent());

        return postList;
    }

    public PostResponse getPost(Long id){
        return readPost.getPost(id);
    }

    @Transactional
    public void updatePost(Long postId, UpdatePostRequest request){
        Post post = updatePost.updatePost(postId, request);
        updatePostTag.postAddTags(post, request.getTagNames());
    }

    public void updatePostStatus(Long postId){
        updatePost.updatePostStatus(postId);
    }

    public void deletePost(Long postId){
        deletePost.deletePost(postId);
    }

}
