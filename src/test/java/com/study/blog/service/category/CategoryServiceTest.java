package com.study.blog.service.category;

import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.service.category.request.CreateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategorySequenceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-category-service-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
}) // SqlGroup을 특정 configuration으로 지정하여 원하는 메서드에만 사용하는 방법?
class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 생성 서비스 검증, 결과 일치 확인")
    public void createCategory_success() {
        // given
        CreateCategoryRequest request = new CreateCategoryRequest("테스트1", "테스트2");

        // when
        categoryService.createCategory(request);

        // then
        Category verifyCategory = categoryRepository.findByIdOrThrow(6L);
        assertThat(verifyCategory.getName()).isEqualTo("테스트1");
        assertThat(verifyCategory.getDescription()).isEqualTo("테스트2");
    }

    @Test
    @DisplayName("카테고리 상태 업데이트 서비스 검증 true -> false")
    public void updateCategoryStatus_success() {
        // given
        long id = 1L;

        // when
        categoryService.updateCategoryStatus(id);

        // then
        Category category = categoryRepository.findByIdOrThrow(id);
        assertThat(category.isStatus()).isEqualTo(false);
    }

    @Test
    @DisplayName("카테고리 순서 변경 검증 1,2,3,4,5 -> 5,4,3,2,1")
    public void updateCategorySequence_success() {
        // given
        LinkedHashSet<Long> idSet = new LinkedHashSet<>(Arrays.asList(5L, 4L, 3L, 2L, 1L));
        UpdateCategorySequenceRequest request = new UpdateCategorySequenceRequest(idSet);

        // when
        categoryService.updateCategorySequence(request);

        List<Category> categoryList = categoryRepository.findAll();
        categoryList.sort(Comparator.comparing(Category::getId));

        // then
        AtomicInteger testValue = new AtomicInteger(5);

        assertThat(categoryList.size()).isNotEqualTo(0);
        categoryList.forEach( category -> {
            assertThat(category.getSequence()).isEqualTo(testValue.getAndDecrement());
        });
    }

    @Test
    @DisplayName("카테고리 순서 변경에서 카테고리 개수 불일치 검증 테스트4, 실제5")
    public void updateCategorySequence_mismatchedCategoryCount_throw() {
        // given
        LinkedHashSet<Long> idSet = new LinkedHashSet<>(Arrays.asList(1L, 2L, 3L, 4L));
        UpdateCategorySequenceRequest request = new UpdateCategorySequenceRequest(idSet);

        // when, then
        assertThatThrownBy(() -> categoryService.updateCategorySequence(request))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("카테고리 업데이트 검증 : 이름, 설명 변경 후 일치 여부 검증")
    public void updateCategory_success() {
        // given
        UpdateCategoryRequest request = new UpdateCategoryRequest(1L, "변경 이름", "변경 설명");

        // when
        categoryService.updateCategory(request);

        // then
        Category category = categoryRepository.findByIdOrThrow(1L);
        assertThat(category.getName()).isEqualTo("변경 이름");
        assertThat(category.getDescription()).isEqualTo("변경 설명");
    }

    @Test
    @DisplayName("카테고리 삭제 여부 검증")
    public void deleteCategory_throw() {
        // given
        long id = 1L;

        // when
        categoryService.deleteCategory(id);

        // then
        assertThatThrownBy(() -> categoryRepository.findByIdOrThrow(1L))
                .isInstanceOf(Exception.class);
    }


}