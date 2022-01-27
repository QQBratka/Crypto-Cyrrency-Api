package com.example.cryptotestproject.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ApiCurrencyResponseDto {
    private String name;
    private BigDecimal price;
    private String usd;
    private LocalDateTime createdAt;
}
