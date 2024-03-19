package com.study.blog.service.category;

import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.service.category.request.CreateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategoryRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // mokito 객체 초기화를 위해 사용했음.
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 생성 서비스 검증, 결과 일치 확인")
    public void createCategory_success() {
        // given
        CreateCategoryRequest request = new CreateCategoryRequest("테스트1", "테스트2");
        when(categoryRepository.getCreateSequenceNumber()).thenReturn(1);

        // when
        categoryService.createCategory(request);
        // save 상태 캡처
        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryCaptor.capture());
        Category savedCategory = categoryCaptor.getValue();

        // then
        assertEquals(1, savedCategory.getSequence());
        assertEquals("테스트1", savedCategory.getName());
        assertEquals("테스트2", savedCategory.getDescription());
    }

    @Test
    @DisplayName("카테고리 상태 업데이트 서비스 검증 true -> false")
    public void updateCategoryStatus_success() {
        // given
        Category category = new Category();
        category.setId(1L);
        category.setStatus(true);
        when(categoryRepository.findByIdOrThrow(1L)).thenReturn(category);

        // when
        categoryService.updateCategoryStatus(1L);

        // save 상태 캡처
        ArgumentCaptor<Category> categoryCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepository).save(categoryCaptor.capture());
        Category savedCategory = categoryCaptor.getValue();

        // then
        assertThat(savedCategory.isStatus()).isEqualTo(false);
    }
}