package com.study.blog.domain.admin.category.service;

import com.study.blog.domain.admin.category.response.CategoryListResponse;
import com.study.blog.domain.admin.category.request.CreateCategoryRequest;
import com.study.blog.domain.admin.category.request.UpdateCategoryRequest;
import com.study.blog.domain.admin.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.admin.category.response.CategoryResponse;
import com.study.blog.infrastructure.persistence.entity.Category;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> getAdminCategoryList() {
        return categoryRepository.getAdminCategoryList();
    }

    public CategoryResponse getCategory(Long id){
        return categoryRepository.findByIdOrThrow(id).toDto();

    }

    @Transactional
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

    @Transactional
    public void updateCategorySequence(UpdateCategorySequenceRequest request){
        LinkedHashSet<Long> idSet = request.getIdSet();

        long categoryCount = categoryRepository.count();
        int requestCount = idSet.size();

        if(categoryCount != requestCount){ throw new IllegalStateException(); }

        List<Category> categoryList = new ArrayList<>();

        int sequence = 1;
        for (Long id : idSet) {
            Category entity = categoryRepository.findByIdOrThrow(id);
            entity.setSequence(sequence);
            categoryList.add(entity);
            sequence++;
        }

        categoryRepository.saveAll(categoryList);

    }


    @Transactional
    public void updateCategory(UpdateCategoryRequest request){

        Category category = categoryRepository.findByIdOrThrow(request.getId());

        category.setName(request.getName());
        category.setDescription(request.getDescription());;
    }

    @Transactional
    public void deleteCategory(Long id){

        categoryRepository.deleteById(id);
    }

}
