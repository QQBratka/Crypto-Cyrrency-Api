package com.example.cryptotestproject.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ApiCurrencyRequestDto {
    private BigDecimal lprice;
    private String curr1;
    private String curr2;
}
