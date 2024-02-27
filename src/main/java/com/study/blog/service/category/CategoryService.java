package com.study.blog.service.category;

import com.study.blog.service.category.response.CategoryListResponse;
import com.study.blog.service.category.request.CreateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategoryRequest;
import com.study.blog.service.category.request.UpdateCategorySequenceRequest;
import com.study.blog.service.category.response.CategoryResponse;
import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Category category = categoryRepository.findByIdOrThrow(id);
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
        Category category = categoryRepository.findByIdOrThrow(request.getId());

        category.setName(request.getName());
        category.setDescription(request.getDescription());;
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
