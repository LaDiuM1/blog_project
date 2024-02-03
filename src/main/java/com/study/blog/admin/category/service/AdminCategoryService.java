package com.study.blog.admin.category.service;

import com.study.blog.admin.category.repository.CategoryRepository;
import com.study.blog.admin.request.CreateCategoryRequest;
import com.study.blog.admin.request.UpdateCategoryRequest;
import com.study.blog.admin.request.UpdateCategorySequenceRequest;
import com.study.blog.admin.request.UpdateCategoryStatusRequest;
import com.study.blog.infrastructure.dto.category.CategoryDto;
import com.study.blog.infrastructure.entity.category.CategoryEntity;
import com.study.blog.infrastructure.persistence.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAdminCategoryList(/*User user*/) {
        return categoryRepository.getAdminCategoryList();
    }

    public CategoryDto getCategory(Long id){
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
    public void updateCategorySequence(List<UpdateCategorySequenceRequest> requestList){

        requestList.forEach( request -> {
            jpaCategoryRepository.findByIdOrThrow(request.getId())
                    .setSequence(request.getSequence());
        });
    }


    @Transactional
    public void updateCategory(UpdateCategoryRequest request){

        jpaCategoryRepository.findByIdOrThrow(request.getId())
                .setName(request.getName())
                .setDescription(request.getDescription());

    }

}
