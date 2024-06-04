package com.study.blog.business.category;

import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import com.study.blog.business.category.exception.CategoryNotFoundException;
import com.study.blog.business.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.study.blog.business.category.CategoryService.CATEGORY_LIST_KEY;

@Component
@RequiredArgsConstructor
public class CategoryReader {

    private final CategoryRepository categoryRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public List<CategoryListDto> getCategoryList() {
        List<CategoryListDto> categoryList = (List<CategoryListDto>) redisTemplate.opsForValue().get(CATEGORY_LIST_KEY);

        if (categoryList == null) {
            categoryList = categoryRepository.getCategoryList();
            redisTemplate.opsForValue().set(CATEGORY_LIST_KEY, categoryList, 60, TimeUnit.MINUTES);
        }

        return categoryList;
    }
    public CategoryDto getCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new).toDto();
    }

}
