package com.example.cryptotestproject.dto.mapper;

import com.example.cryptotestproject.dto.ApiCurrencyRequestDto;
import com.example.cryptotestproject.dto.ApiCurrencyResponseDto;
import com.example.cryptotestproject.model.CryptoCurrency;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class CryptoCurrencyMapper {
    public CryptoCurrency toModel(ApiCurrencyRequestDto currencyDto) {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setName(currencyDto.getCurr1());
        cryptoCurrency.setPrice(currencyDto.getLprice());
        cryptoCurrency.setUsd(currencyDto.getCurr2());
        cryptoCurrency.setCreatedAt(LocalDateTime.now());
        return cryptoCurrency;
    }

    public ApiCurrencyResponseDto toDto(CryptoCurrency currency) {
        ApiCurrencyResponseDto responseDto = new ApiCurrencyResponseDto();
        responseDto.setName(currency.getName());
        responseDto.setUsd(currency.getUsd());
        responseDto.setPrice(currency.getPrice());
        responseDto.setCreatedAt(currency.getCreatedAt());
        return responseDto;
    }
}
