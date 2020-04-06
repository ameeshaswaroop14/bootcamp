package com.commerceApp.commerceApp.validators;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidGST {

    String message()  default "Invalid GST";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
