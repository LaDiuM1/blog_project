package com.study.blog.domain.category;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.request.CreateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategorySequenceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@SqlGroup({
        @Sql(value = "/sql/test-category-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 생성 서비스 검증")
    public void createCategory_success() {
        // given
        String testName = "카테고리 생성 테스트 이름";
        String testDescription = "카테고리 생성 테스트 설명";
        CreateCategoryRequest request = new CreateCategoryRequest(testName, testDescription);

        // when
        categoryService.createCategory(request);

        // then
        Category verifyCreateCategory = categoryRepository.findByIdOrThrow(categoryRepository.count());
        assertThat(verifyCreateCategory.getName()).isEqualTo(request.getName());
        assertThat(verifyCreateCategory.getDescription()).isEqualTo(request.getDescription());
    }

    @Test
    @DisplayName("카테고리 상태 업데이트 서비스 검증 true -> false")
    public void updateCategoryStatus_success() {
        // given
        long id = 1L;
        boolean beforeStatus = categoryRepository.findById(id).get().isStatus();

        // when
        categoryService.updateCategoryStatus(id);

        // then
        boolean afterStatus = categoryRepository.findById(id).get().isStatus();
        assertThat(afterStatus).isNotEqualTo(beforeStatus);
    }

    @Test
    @DisplayName("카테고리 순서 변경 검증")
    public void updateCategorySequence_success() {
        // given
        Map<Integer, Long> sequenceAndIdMap = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .collect(Collectors.toMap(Category::getSequence, Category::getId));

        List<Integer> updateSequence = new ArrayList<>();

        sequenceAndIdMap.forEach( (sequence, id) -> {
            if(sequence % sequenceAndIdMap.size() != 0){
                updateSequence.add(sequence+1);
            }else{
                updateSequence.add(sequence % sequenceAndIdMap.size()+1);
            }
        });

        LinkedHashSet<Long> updateSequenceIdSet = updateSequence
                .stream()
                .map(sequenceAndIdMap::get).collect(Collectors.toCollection(LinkedHashSet::new));

        UpdateCategorySequenceRequest request = new UpdateCategorySequenceRequest(updateSequenceIdSet);

        // when
        categoryService.updateCategorySequence(request);

        List<Category> categoryList = categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "sequence"));

        // then
        assertThat(categoryList.size()).isNotEqualTo(0);
        int index = 0;
        for (Long updateSequenceId : updateSequenceIdSet) {
            assertThat(categoryList.get(index++).getId()).isEqualTo(updateSequenceId);
        }
    }

    @Test
    @DisplayName("카테고리 순서 변경 검증, 요청 카테고리 수 불일치 -> throw")
    public void updateCategorySequence_unmatchedCount_throw() {
        // given
        LinkedHashSet<Long> unmatchedCategorySet = categoryRepository.findAll()
                .stream().map(Category::getId)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        int verifyCount = unmatchedCategorySet.size();

        unmatchedCategorySet.remove(unmatchedCategorySet.iterator().next()); // 첫번째 요소 제거
        UpdateCategorySequenceRequest request = new UpdateCategorySequenceRequest(unmatchedCategorySet);

        // when, then
        assertThat(request.getIdSet().size()).isNotZero();
        assertThat(request.getIdSet().size()).isNotEqualTo(verifyCount);
        assertThatThrownBy(() ->
            categoryService.updateCategorySequence(request)
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("카테고리 업데이트 검증 : 이름, 설명 변경 후 일치 여부 검증")
    public void updateCategory_success() {
        // given
        Long updateCategoryId = 1L;
        String flag = "verifyUpdate";
        Category beforeUpdateCategory = categoryRepository.findById(updateCategoryId).get();
        String updateCategoryName = beforeUpdateCategory.getName()+flag;
        String updateCategoryDescription = beforeUpdateCategory.getDescription()+flag;
        UpdateCategoryRequest request = new UpdateCategoryRequest(updateCategoryId, updateCategoryName, updateCategoryDescription);

        // when
        categoryService.updateCategory(request);

        // then
        Category afterUpdateCategory = categoryRepository.findById(updateCategoryId).get();
        assertThat(afterUpdateCategory.getName()).isEqualTo(updateCategoryName);
        assertThat(afterUpdateCategory.getDescription()).isEqualTo(updateCategoryDescription);
    }

    @Test
    @DisplayName("카테고리 업데이트 검증, 존재하지 않는 id -> throw")
    public void updateCategory_notExistingId_throw() {
        // given
        long notExistingCategoryId = categoryRepository.count()+1;
        UpdateCategoryRequest request = new UpdateCategoryRequest(notExistingCategoryId, new String(), new String());
        // when, then
        assertThatThrownBy(() -> categoryService.updateCategory(request))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @DisplayName("카테고리 삭제 여부 검증")
    public void deleteCategory_success() {
        // given
        long id = 1L;
        boolean beforeDeleteCategory = categoryRepository.findById(id).isPresent();
        // when
        categoryService.deleteCategory(id);

        // then
        boolean afterDeleteCategory = categoryRepository.findById(id).isPresent();
        assertThat(beforeDeleteCategory).isTrue();
        assertThat(afterDeleteCategory).isFalse();
    }

    @Test
    @DisplayName("카테고리 삭제 여부 검증, 존재하지 않는 id -> throw")
    public void deleteCategory_throw() {
        // given
        long notExistingCategoryId = categoryRepository.count()+1;

        // when, then
        assertThatThrownBy(() -> categoryService.deleteCategory(notExistingCategoryId))
                .isInstanceOf(EntityNotFoundException.class);
    }


}