package com.commerceApp.commerceApp.validators;

import com.commerceApp.commerceApp.dtos.ForgotPassword;
import com.commerceApp.commerceApp.dtos.registrationDtos.CustomerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.SellerRegistrationDto;
import com.commerceApp.commerceApp.dtos.registrationDtos.UserRegistrationDto;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){

        if(obj instanceof SellerRegistrationDto){
            SellerRegistrationDto seller = (SellerRegistrationDto) obj;
            return seller.getPassword().equals(seller.getConfirmPassword());
        }
        else if(obj instanceof ForgotPassword){
            ForgotPassword passwords = (ForgotPassword) obj;
            return passwords.getPassword().equals(passwords.getConfirmPassword());
        }

        CustomerRegistrationDto customer = (CustomerRegistrationDto) obj;
        return customer.getPassword().equals(customer.getConfirmPassword());
    }
}
