package com.codingboot.Core.domain.validator;

import com.codingboot.Core.domain.annotation.Password;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        if(!StringUtils.hasText(password)) {
            return false;
        }

        if(password.trim().length() < 6) {
            return false;
        }
        return true;
    }
}