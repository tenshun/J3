package com.tenshun.web.form.validation;


import com.tenshun.web.form.RegForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        RegForm form = (RegForm) obj;
        return form.getPassword().equals(form.getMatchingPassword());
    }
}