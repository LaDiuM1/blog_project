package com.study.blog.business.post;

import com.study.blog.business.post.data.PostCreateData;
import com.study.blog.business.post.data.PostSearchData;
import com.study.blog.business.post.data.PostUpdateData;
import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.dto.PostListDto;
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
    public Long createPost(PostCreateData createData){
        Post createdPost = createPost.createPost(createData);
        updatePostTag.postAddTags(createdPost, createData.getTagNames());
        return createdPost.getId();
    }

    public Page<PostListDto> searchPostList(PostSearchData searchData, Pageable pageable){
        Page<PostListDto> postList = readPost.searchPostList(searchData, pageable);
        updatePostTag.matchPostAndTags(postList.getContent());

        return postList;
    }

    public PostDto getPost(Long postId){
        return readPost.getPost(postId);
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateData updateData){
        Post post = updatePost.updatePost(postId, updateData);
        updatePostTag.postAddTags(post, updateData.getTagNames());
    }

    public void updatePostStatus(Long postId){
        updatePost.updatePostStatus(postId);
    }

    public void deletePost(Long postId){
        deletePost.deletePost(postId);
    }

}
