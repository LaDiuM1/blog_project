package com.study.blog.business.tag;

import com.study.blog.business.tag.dto.SearchTagDto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class TagRepositoryImplTest {
    @Autowired
    private TagRepository tagRepository;

    // 서비스로 이동 필요
    @Test
    @DisplayName("태그 검색 Repository, 검색 값 일치 여부 확인")
    @Sql(value = "/sql/test-truncate-all.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void searchTag_success() {
        // given
        Set<Tag> testTagSet = new HashSet<>();
        testTagSet.add(new Tag("검색1"));
        testTagSet.add(new Tag("검색2"));
        testTagSet.add(new Tag("검색3"));
        testTagSet.add(new Tag("태그1"));
        testTagSet.add(new Tag("태그2"));
        tagRepository.saveAll(testTagSet);
        String testKeyword = "검색";

        // when
        List<SearchTagDto> tagResponseList = tagRepository.searchTag(testKeyword);
        boolean verifyTagName1 = tagResponseList.stream().anyMatch( tag -> tag.getName().equals("검색1"));
        boolean verifyTagName2 = tagResponseList.stream().anyMatch( tag -> tag.getName().equals("검색2"));
        boolean verifyTagName3 = tagResponseList.stream().anyMatch( tag -> tag.getName().equals("검색3"));

        // then
        assertThat(tagResponseList.size()).isEqualTo(3);
        AssertionsForClassTypes.assertThat(verifyTagName1).isTrue();
        AssertionsForClassTypes.assertThat(verifyTagName2).isTrue();
        AssertionsForClassTypes.assertThat(verifyTagName3).isTrue();
    }
}