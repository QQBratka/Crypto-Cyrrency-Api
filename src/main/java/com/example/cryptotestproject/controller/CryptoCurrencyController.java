package com.example.cryptotestproject.controller;

import com.example.cryptotestproject.dto.ApiCurrencyResponseDto;
import com.example.cryptotestproject.dto.mapper.CryptoCurrencyMapper;
import com.example.cryptotestproject.service.CryptoCurrencyService;
import com.example.cryptotestproject.validation.ValidName;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
@Validated
public class CryptoCurrencyController {
    private final CryptoCurrencyMapper currencyMapper;
    private final CryptoCurrencyService currencyService;

    public CryptoCurrencyController(CryptoCurrencyMapper currencyMapper,
                                    CryptoCurrencyService currencyService) {
        this.currencyMapper = currencyMapper;
        this.currencyService = currencyService;
    }

    @GetMapping("/minprice")
    public ApiCurrencyResponseDto getMinValueByName(@RequestParam @ValidName String name) {
        return currencyMapper.toDto(currencyService.findMinPriceByName(name));
    }

    @GetMapping("/maxprice")
    public ApiCurrencyResponseDto getMaxValueByName(@RequestParam @ValidName String name) {
        return currencyMapper.toDto(currencyService.findMaxPriceByName(name));
    }

    @GetMapping
    public List<ApiCurrencyResponseDto> findByName(
            @RequestParam @ValidName String name,
            @RequestParam (defaultValue = "0") Integer page,
            @RequestParam (defaultValue = "10") Integer size,
            @RequestParam (defaultValue = "price") String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
            return currencyService.findAllByName(name, pageRequest).stream()
                    .map(currencyMapper::toDto)
                    .collect(Collectors.toList());

    }

    @GetMapping("/csv")
    public String writeReport() {
        currencyService.createCsv();
        return "Report was successfully written to report.csv file!";
    }
}
