package com.study.blog.admin.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.blog.model.entity.category.QCategory;
import com.study.blog.model.persistence.category.JpaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void getCreateSequenceNumber() {
        int sequenceNumber = categoryRepository.getCreateSequenceNumber();

        System.out.println("sequenceNumber = " + sequenceNumber);
    }
}