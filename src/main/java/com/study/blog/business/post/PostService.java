package com.study.blog.business.post;

import com.study.blog.business.post.data.PostCreateData;
import com.study.blog.business.post.data.PostSearchData;
import com.study.blog.business.post.data.PostUpdateData;
import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.dto.PostListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostReader postReader;
    private final PostCreator postCreator;
    private final PostUpdater postUpdater;
    private final DeletePost deletePost;
    private final PostTagUpdater postTagUpdater;
    private final PostRedisService postRedisService;

    private CacheManager cacheManager;
    @Transactional
    public Long createPost(PostCreateData createData){
        Post createdPost = postCreator.createPost(createData);
        postTagUpdater.postAddTags(createdPost, createData.getTagNames());
        return createdPost.getId();
    }

    public Page<PostListDto> searchPostList(PostSearchData searchData, Pageable pageable){
//        // 고유한 캐시 키 생성
//        String cacheKey = "searchPostList_" + searchData.toString() + "_" + pageable.toString();
//        // 캐시 조회
//        Cache cache = cacheManager.getCache("searchPosts");
//        if (cache != null) {
//            Page<PostListDto> cachedResult = cache.get(cacheKey, Page.class);
//            if (cachedResult != null) {
//                return cachedResult;
//            }
//        }
//
//        // 실제 데이터 조회
//        Page<PostListDto> postList = readPost.searchPostList(searchData, pageable);
//        updatePostTag.matchPostAndTags(postList.getContent());
//
//        // 캐시에 저장
//        if (cache != null) {
//            cache.put(cacheKey, postList);
//        }
//
//        return postList;
        Page<PostListDto> postList = postReader.searchPostList(searchData, pageable);
        postTagUpdater.matchPostAndTags(postList.getContent());

        return postList;
    }

    @Transactional
    public PostDto getPost(Long postId){
        postRedisService.incrementViewCount(postId);
        Optional<PostDto> optionalCachedDto = postRedisService.getCachedPost(postId);

        return optionalCachedDto.orElseGet(() -> postReader.getPost(postId));
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateData updateData){
        Post post = postUpdater.updatePost(postId, updateData);
        postTagUpdater.postAddTags(post, updateData.getTagNames());
    }

    public void updatePostStatus(Long postId){
        postUpdater.updatePostStatus(postId);
    }

    public void deletePost(Long postId){
        deletePost.deletePost(postId);
    }

}
