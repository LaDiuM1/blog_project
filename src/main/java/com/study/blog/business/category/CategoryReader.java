package com.study.blog.business.category;

import com.study.blog.business.category.dto.CategoryDto;
import com.study.blog.business.category.dto.CategoryListDto;
import com.study.blog.business.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryReader {

    private final CategoryRepository categoryRepository;

    public List<CategoryListDto> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    public CategoryDto getCategory(Long id){
        return categoryRepository.findByIdOrThrow(id).toResponse();
    }

}
