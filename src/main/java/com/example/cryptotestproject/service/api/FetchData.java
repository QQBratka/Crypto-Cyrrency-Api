package com.example.cryptotestproject.service.api;

import com.example.cryptotestproject.dto.ApiCurrencyRequestDto;
import com.example.cryptotestproject.dto.mapper.CryptoCurrencyMapper;
import com.example.cryptotestproject.service.CryptoCurrencyService;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FetchData {
    private final HttpClient httpClient;
    private final CryptoCurrencyService currencyService;
    private final CryptoCurrencyMapper currencyMapper;

    public FetchData(HttpClient httpClient,
                     CryptoCurrencyService currencyService,
                     CryptoCurrencyMapper currencyMapper) {
        this.httpClient = httpClient;
        this.currencyService = currencyService;
        this.currencyMapper = currencyMapper;
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchInfo() {
        log.info("last prices for crypto currencies was fetched to db " + LocalDateTime.now());
        ApiCurrencyRequestDto btcCurrency
                = httpClient.get("https://cex.io/api/last_price/BTC/USD", ApiCurrencyRequestDto.class);
        currencyService.add(currencyMapper.toModel(btcCurrency));
        log.info(btcCurrency);
        ApiCurrencyRequestDto ethCurrency
                = httpClient.get("https://cex.io/api/last_price/ETH/USD", ApiCurrencyRequestDto.class);
        currencyService.add(currencyMapper.toModel(ethCurrency));
        log.info(ethCurrency);
        ApiCurrencyRequestDto xrpCurrency
                = httpClient.get("https://cex.io/api/last_price/XRP/USD", ApiCurrencyRequestDto.class);
        currencyService.add(currencyMapper.toModel(xrpCurrency));
        log.info(xrpCurrency);
    }
}
