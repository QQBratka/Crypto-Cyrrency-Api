package com.example.cryptotestproject.service;

import com.example.cryptotestproject.model.CryptoCurrency;
import com.example.cryptotestproject.repository.CryptoCurrencyRepository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    private final CryptoCurrencyRepository currencyRepository;
    private final String fileName = "report.csv";

    public CryptoCurrencyServiceImpl(CryptoCurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public CryptoCurrency add(CryptoCurrency cryptoCurrency) {
        cryptoCurrency.setCreatedAt(LocalDateTime.now());
        return currencyRepository.save(cryptoCurrency);
    }

    @Override
    public CryptoCurrency findMaxPriceByName(String name) {
        return currencyRepository.findFirstByNameOrderByPriceDesc(name);
    }

    @Override
    public CryptoCurrency findMinPriceByName(String name) {
        return currencyRepository.findFirstByNameOrderByPrice(name);
    }

    @Override
    public List<CryptoCurrency> findAllByName(String name, Pageable pageable) {
        return currencyRepository.findAllByName(name, pageable);
    }

    @Override
    public void createCsv() {
        String fileName = "report.csv";
        StringBuilder report = new StringBuilder("Name, Min Price, Max Price");
        report.append(System.lineSeparator())
                .append("BTC, ").append(findMinPriceByName("BTC").getPrice())
                .append(", ").append(findMaxPriceByName("BTC").getPrice())
                .append(System.lineSeparator())
                .append("ETH, ").append(findMinPriceByName("ETH").getPrice())
                .append(", ").append(findMaxPriceByName("ETH").getPrice())
                .append(System.lineSeparator())
                .append("XRP, ").append(findMinPriceByName("XRP").getPrice())
                .append(", ").append(findMaxPriceByName("XRP").getPrice());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't create file!", e);
        }
    }
}
