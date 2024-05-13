package com.study.blog.business.post.dto;

import com.study.blog.business.tag.dto.TagDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private String title;
    private String content;
    private Boolean status;
    private String categoryName;
    private Long commentCount;
    @Setter
    private List<TagDto> tags;

    public PostListDto(Long id, String title, String content, Boolean status, String categoryName, Long commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.categoryName = categoryName;
        this.commentCount = commentCount;
    }


}
