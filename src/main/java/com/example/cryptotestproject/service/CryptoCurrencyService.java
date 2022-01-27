package com.example.cryptotestproject.service;

import com.example.cryptotestproject.model.CryptoCurrency;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CryptoCurrencyService {
    CryptoCurrency add(CryptoCurrency cryptoCurrency);

    CryptoCurrency findMaxPriceByName(String name);

    CryptoCurrency findMinPriceByName(String name);

    List<CryptoCurrency> findAllByName(String name, Pageable pageable);

    void createCsv();
}
