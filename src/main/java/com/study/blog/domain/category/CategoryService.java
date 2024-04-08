package com.study.blog.domain.category;

import com.study.blog.domain.category.repository.Category;
import com.study.blog.domain.category.repository.CategoryRepository;
import com.study.blog.domain.category.response.CategoryListResponse;
import com.study.blog.domain.category.request.CreateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategoryRequest;
import com.study.blog.domain.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> getAdminCategoryList() {
        return categoryRepository.getAdminCategoryList();
    }

    public CategoryResponse getCategory(Long id){
        return categoryRepository.findByIdOrThrow(id).toDto();
    }

    public void createCategory(CreateCategoryRequest request){
        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        Category category = new Category(
                request.getName(),
                request.getDescription(),
                sequenceNumber
        );
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategoryStatus(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        category.setStatus(!category.isStatus());
    }

    public void updateCategorySequence(UpdateCategorySequenceRequest request){
        LinkedHashSet<Long> idSet = request.getIdSet();

        boolean updateValid = categoryRepository.updateCategoryValid(idSet);
        if(updateValid){ throw new IllegalStateException(); }

        categoryRepository.updateCategorySequence(idSet);
    }

    @Transactional
    public void updateCategory(UpdateCategoryRequest request){
        Category category = categoryRepository.findById(request.getId())
                .orElseThrow(EntityNotFoundException::new);
        category.setName(request.getName());
        category.setDescription(request.getDescription());

    }

    public void deleteCategory(Long id){
        boolean existingCategoryCheck = categoryRepository.existsById(id);

        if(!existingCategoryCheck) { throw new EntityNotFoundException(); }
        categoryRepository.deleteById(id);
    }

}
