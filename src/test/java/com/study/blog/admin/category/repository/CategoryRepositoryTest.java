package com.study.blog.admin.category.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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