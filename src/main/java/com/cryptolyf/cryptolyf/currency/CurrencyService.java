package com.cryptolyf.cryptolyf.currency;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
public class CurrencyService {

    public List<Currency> getCurrencies() {
        return List.of(
                new Currency(
                        1L,
                        "bitcoin",
                        new BigDecimal("0.00001"),
                        LocalDateTime.of(2021, Month.MARCH, 8, 14, 5, 5),
                        "Hardware Wallet",
                        new BigDecimal("2232.03141")
                )
        );
    }
}
