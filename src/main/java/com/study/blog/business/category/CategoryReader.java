package com.study.blog.business.category;

import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import com.study.blog.business.category.exception.CategoryNotFoundException;
import com.study.blog.business.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryReader {

    private final CategoryRepository categoryRepository;

    public List<CategoryListDto> getCategoryList() {
        return categoryRepository.getCategoryList();
    }
    public CategoryDto getCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new).toDto();
    }

}
