package com.commerceApp.commerceApp.validators;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component

public class GSTValidator
        implements ConstraintValidator<ValidGst, String> {

    private Pattern pattern;
    private Matcher matcher;

    private static final String GST_PATTERN = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";



    @Override
    public boolean isValid(String GST, ConstraintValidatorContext context){
        return (validateGST(GST));
    }

    private boolean validateGST(String GST) {
        pattern = Pattern.compile(GST_PATTERN);
        matcher = pattern.matcher(GST);
        return matcher.matches();
    }
}