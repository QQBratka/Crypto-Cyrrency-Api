package com.example.cryptotestproject.validation;

public class Validation {
    public boolean isValid(String name) {
        return name.matches("BTC|ETH|XRP");
    }
}
