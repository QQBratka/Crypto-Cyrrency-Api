package com.example.cryptotestproject.controller;

import com.example.cryptotestproject.dto.ApiCurrencyRequestDto;
import com.example.cryptotestproject.dto.mapper.CryptoCurrencyMapper;
import com.example.cryptotestproject.service.CryptoCurrencyService;
import com.example.cryptotestproject.service.api.HttpClient;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class PostController {
    private final HttpClient httpClient;
    private final CryptoCurrencyService currencyService;
    private final CryptoCurrencyMapper currencyMapper;

    public PostController(HttpClient httpClient,
                          CryptoCurrencyService currencyService,
                          CryptoCurrencyMapper currencyMapper) {
        this.httpClient = httpClient;
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
    }

    @PostConstruct
    public void fetchData() {
        ApiCurrencyRequestDto btc
                = httpClient.get("https://cex.io/api/last_price/BTC/USD",
                ApiCurrencyRequestDto.class);
        log.info("{}", btc);
        ApiCurrencyRequestDto eth
                = httpClient.get("https://cex.io/api/last_price/ETH/USD",
                ApiCurrencyRequestDto.class);
        log.info("{}", eth);
        ApiCurrencyRequestDto xrp
                = httpClient.get("https://cex.io/api/last_price/XRP/USD",
                ApiCurrencyRequestDto.class);
        log.info("{}", xrp);
        currencyService.add(currencyMapper.toModel(btc));
        currencyService.add(currencyMapper.toModel(eth));
        currencyService.add(currencyMapper.toModel(xrp));
    }
}
