package com.study.blog.service.post.request.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class TagNameSetValidator implements ConstraintValidator<TagNamesValid, Set<String>> {

    public boolean isValid(Set<String> tagNameSet, ConstraintValidatorContext context) {
        if (tagNameSet == null) {
            return true;
        }
        for (String tagName : tagNameSet) {
            if (tagName == null || tagName.trim().isEmpty()) {
                // 공백으로만 이루어진 경우 유효성 검사 실패
                return false;
            }
        }
        return true;
    }
}
