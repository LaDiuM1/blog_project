package com.study.blog.business.post;

import com.study.blog.business.post.dto.PostDto;
import com.study.blog.business.post.exception.PostNotFoundException;
import com.study.blog.business.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostRedisService {

    private final PostRepository postRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private static final Double APPLY_CACHE_RATIO = 0.2;
    private static final String GET_POST_KEY_PREFIX = "post:get:";
    private static final String VIEW_COUNT_KEY_PREFIX = "post:viewCount:";

    public Optional<PostDto> getCachedPost(Long postId) {
        String cacheKey = GET_POST_KEY_PREFIX + postId;
        Object cachedPost = redisTemplate.opsForValue().get(cacheKey);

        if(cachedPost instanceof PostDto){
            return Optional.of((PostDto) cachedPost);
        }

        return Optional.empty();
    }
    public void incrementViewCount(Long postId) {
        redisTemplate.opsForZSet().incrementScore(VIEW_COUNT_KEY_PREFIX, postId, 1);
    }

    @Transactional
    @Scheduled(cron = "0 * * * * *") // 매 시간 정각에 설정된 조회수 랭크 비율에 따라 게시물들을 캐시에 적용하는 메서드
    public void updateTopPostsCache() {
        // 캐시에서 조회수가 카운트된 모든 게시물의 숫자 계산
        long totalPosts = Optional.ofNullable(redisTemplate.opsForZSet().size(VIEW_COUNT_KEY_PREFIX)).orElse(0L);
        System.out.println("totalPosts = " + totalPosts);
        // 비율에 따른 캐시 적용 개수 설정
        long rankRatioCount = (long) Math.ceil(totalPosts * APPLY_CACHE_RATIO);
        // 적용 비율에 따른 상위 게시물 조회
        Set<Object> topPosts = Optional.ofNullable(
                redisTemplate.opsForZSet().reverseRange(VIEW_COUNT_KEY_PREFIX, 0, rankRatioCount - 1))
                .orElse(new HashSet<>());
        // 현재 캐시된 게시물 전체 키 호출
        Set<String> currentCacheKeys = Optional.ofNullable(redisTemplate.keys(GET_POST_KEY_PREFIX + "*"))
                .orElse(new HashSet<>());
        // 이전 캐시된 게시물과 비교를 위한 Set
        Set<String> newCacheKeys = new HashSet<>();
        // 캐시되지 않은 게시물을 배치로 찾기 위한 id 저장 set
        Set<Long> newCachedPostId = new HashSet<>();
        // 상위 게시물을 캐시에 저장, 캐시에 없는 게시물의 경우 배치 id set에 추가
        for (Object postId : topPosts) {
            String cacheKey = GET_POST_KEY_PREFIX + postId;
            newCacheKeys.add(cacheKey);
            if (!currentCacheKeys.contains(cacheKey)) {
                newCachedPostId.add(((Number) postId).longValue());
            }
        }

        if (!newCachedPostId.isEmpty()) {
            // 캐시되지 않은 게시물을 배치로 검색하여 저장
            Set<PostDto> newCachedPosts = postRepository.findPosts(newCachedPostId);
            // 찾아온 신규 캐시 게시글들을 순회하여 캐시에 저장
            newCachedPosts.forEach(post -> redisTemplate.opsForValue().set(GET_POST_KEY_PREFIX + post.getId(), post));
        }

        // 현재 게시물과 이전 캐시된 게시물을 비교하여 없는 캐시들은 캐시에서 삭제
        for (String cacheKey : currentCacheKeys) {
            if (!newCacheKeys.contains(cacheKey)) {
                redisTemplate.delete(cacheKey);
            }
        }

        // 조회수 초기화
        redisTemplate.delete(VIEW_COUNT_KEY_PREFIX);
//        redisTemplate.delete(GET_POST_KEY_PREFIX + "*");
        // 캐시된 사이즈 출력 (테스트용)
        System.out.println("cachedSize = " + Optional.ofNullable(redisTemplate.opsForZSet().size(VIEW_COUNT_KEY_PREFIX)).orElse(0L));
    }

}
