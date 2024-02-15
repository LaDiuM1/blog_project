package com.study.blog.admin.category.repository;

import com.study.blog.infrastructure.persistence.repository.category.CategoryRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryRepositoryImplTest {
    @Autowired
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Test
    void getCreateSequenceNumber() {
        int sequenceNumber = categoryRepositoryImpl.getCreateSequenceNumber();

        System.out.println("sequenceNumber = " + sequenceNumber);
    }
}