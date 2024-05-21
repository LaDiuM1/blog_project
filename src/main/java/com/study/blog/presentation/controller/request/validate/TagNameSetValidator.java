package com.study.blog.presentation.controller.request.validate;

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
                return false;
            }
        }
        return true;
    }
}
