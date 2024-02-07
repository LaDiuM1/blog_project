package com.study.blog.domain.admin.category.service;

import com.study.blog.domain.admin.category.response.CategoryListResponse;
import com.study.blog.infrastructure.persistence.repository.category.CategoryRepository;
import com.study.blog.domain.admin.category.request.CreateCategoryRequest;
import com.study.blog.domain.admin.category.request.UpdateCategoryRequest;
import com.study.blog.domain.admin.category.request.UpdateCategorySequenceRequest;
import com.study.blog.domain.admin.category.request.UpdateCategoryStatusRequest;
import com.study.blog.domain.admin.category.response.CategoryResponse;
import com.study.blog.infrastructure.persistence.entity.category.CategoryEntity;
import com.study.blog.infrastructure.persistence.repository.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryRepository categoryRepository;

    public List<CategoryListResponse> getAdminCategoryList() {
        return categoryRepository.getAdminCategoryList();
    }

    public CategoryResponse getCategory(Long id){
        return jpaCategoryRepository.findByIdOrThrow(id).toDto();

    }

    @Transactional
    public void createCategory(CreateCategoryRequest request){

        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        CategoryEntity categoryEntity = new CategoryEntity(
                request.getName(),
                request.getDescription(),
                sequenceNumber
        );

        jpaCategoryRepository.save(categoryEntity);

    }

    @Transactional
    public void updateCategoryStatus(UpdateCategoryStatusRequest request){

        jpaCategoryRepository.findByIdOrThrow(request.getId())
                .setStatus(!request.getStatus());

    }

    @Transactional
    public void updateCategorySequence(UpdateCategorySequenceRequest request){
        LinkedHashSet<Long> idSet = request.getIdSet();

        long categoryCount = jpaCategoryRepository.count();
        int requestCount = idSet.size();

        if(categoryCount != requestCount){ throw new IllegalStateException(); }

        List<CategoryEntity> categoryEntityList = new ArrayList<>();

        int sequence = 1;
        for (Long id : idSet) {
            CategoryEntity entity = jpaCategoryRepository.findByIdOrThrow(id).setSequence(sequence);
            categoryEntityList.add(entity);
            sequence++;
        }

        jpaCategoryRepository.saveAll(categoryEntityList);

    }


    @Transactional
    public void updateCategory(UpdateCategoryRequest request){

        jpaCategoryRepository.findByIdOrThrow(request.getId())
                .setName(request.getName())
                .setDescription(request.getDescription());

    }

    @Transactional
    public void deleteCategory(Long id){

        jpaCategoryRepository.deleteById(id);

    }

}
