package com.example.cryptotestproject.validation;

import com.example.cryptotestproject.exception.ApiRequestException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NameValidator implements ConstraintValidator<ValidName, String> {
    private final List<String> correctNames = List.of("BTC", "ETH", "XRP");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (!correctNames.contains(s)) {
            throw new ApiRequestException("Invalid name of currency, please choose among BTC, XRP, ETH");
        }
        return correctNames.contains(s);
    }
}
