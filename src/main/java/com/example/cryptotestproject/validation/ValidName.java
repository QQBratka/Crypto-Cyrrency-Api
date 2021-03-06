package com.example.cryptotestproject.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.TYPE, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Invalid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
