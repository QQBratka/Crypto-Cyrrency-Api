package com.example.cryptotestproject.repository;

import com.example.cryptotestproject.model.CryptoCurrency;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    CryptoCurrency findFirstByNameOrderByPrice(String name);

    CryptoCurrency findFirstByNameOrderByPriceDesc(String name);

    List<CryptoCurrency> findAllByName(String name, Pageable pageable);
}
