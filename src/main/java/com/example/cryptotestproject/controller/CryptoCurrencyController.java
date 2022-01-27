package com.example.cryptotestproject.controller;

import com.example.cryptotestproject.dto.ApiCurrencyResponseDto;
import com.example.cryptotestproject.dto.mapper.CryptoCurrencyMapper;
import com.example.cryptotestproject.exception.ApiRequestException;
import com.example.cryptotestproject.service.CryptoCurrencyService;
import com.example.cryptotestproject.validation.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
public class CryptoCurrencyController {
    private final Validation validation = new Validation();
    private final CryptoCurrencyMapper currencyMapper;
    private final CryptoCurrencyService currencyService;

    public CryptoCurrencyController(CryptoCurrencyMapper currencyMapper,
                                    CryptoCurrencyService currencyService) {
        this.currencyMapper = currencyMapper;
        this.currencyService = currencyService;
    }

    @GetMapping("/minprice")
    public ApiCurrencyResponseDto getMinValueByName(@RequestParam String name) {
        if (validation.isValid(name)) {
            return currencyMapper.toDto(currencyService.findMinPriceByName(name));
        }
        throw new ApiRequestException("Invalid currency name, please choose among BTC ETH XRP");
    }

    @GetMapping("/maxprice")
    public ApiCurrencyResponseDto getMaxValueByName(@RequestParam String name) {
        if (validation.isValid(name)) {
            return currencyMapper.toDto(currencyService.findMaxPriceByName(name));
        }
        throw new ApiRequestException("Invalid currency name, please choose among BTC ETH XRP");
    }

    @GetMapping
    public List<ApiCurrencyResponseDto> findByName(
            @RequestParam String name,
            @RequestParam (defaultValue = "0") Integer page,
            @RequestParam (defaultValue = "10") Integer size,
            @RequestParam (defaultValue = "price") String sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(":")) {
            String[] sortingFields = sortBy.split(";");
            for (String field : sortingFields) {
                Sort.Order order;
                if (field.contains(":")) {
                    String[] fieldsAndDirections = field.split(":");
                    order = new Sort.Order(Sort.Direction.valueOf(fieldsAndDirections[1]),
                            fieldsAndDirections[0]);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, field);
                }
                orders.add(order);
            }
        } else {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, sortBy);
            orders.add(order);
        }
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        if (validation.isValid(name)) {
            return currencyService.findAllByName(name, pageRequest).stream()
                    .map(currencyMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw new ApiRequestException("Invalid currency name, please choose among BTC ETH XRP");
    }

    @GetMapping("/csv")
    public String writeReport() {
        currencyService.createCsv();
        return "Report was successfully written to report.csv file!";
    }
}
