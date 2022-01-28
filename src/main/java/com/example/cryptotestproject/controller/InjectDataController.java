package com.example.cryptotestproject.controller;

import com.example.cryptotestproject.dto.ApiCurrencyRequestDto;
import com.example.cryptotestproject.dto.mapper.CryptoCurrencyMapper;
import com.example.cryptotestproject.service.CryptoCurrencyService;
import com.example.cryptotestproject.service.api.HttpClientService;
import javax.annotation.PostConstruct;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class InjectDataController {
    @Value("${value.btc}")
    private String btcValue;
    @Value("${value.eth}")
    private String ethValue;
    @Value("${value.xrp}")
    private String xrpValue;
    private final HttpClientService httpClient;
    private final CryptoCurrencyService currencyService;
    private final CryptoCurrencyMapper currencyMapper;

    public InjectDataController(HttpClientService httpClient,
                                CryptoCurrencyService currencyService,
                                CryptoCurrencyMapper currencyMapper) {
        this.httpClient = httpClient;
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
    }

    @PostConstruct
    public void fetchData() {
        ApiCurrencyRequestDto btc
                = httpClient.get(btcValue, ApiCurrencyRequestDto.class);
        log.info(btc);
        currencyService.add(currencyMapper.toModel(btc));
        ApiCurrencyRequestDto eth
                = httpClient.get(ethValue, ApiCurrencyRequestDto.class);
        log.info(eth);
        currencyService.add(currencyMapper.toModel(eth));
        ApiCurrencyRequestDto xrp
                = httpClient.get(xrpValue, ApiCurrencyRequestDto.class);
        log.info(xrp);
        currencyService.add(currencyMapper.toModel(xrp));
    }
}
