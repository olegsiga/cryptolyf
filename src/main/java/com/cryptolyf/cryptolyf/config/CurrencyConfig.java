package com.cryptolyf.cryptolyf.config;

import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class CurrencyConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            CurrencyRepository repository) {
        return args -> {
            Currency bitcoin = new Currency(
                    "BTC",
                    new BigDecimal("0.1337"),
                    LocalDateTime.now(),
                    "Hardware Wallet",
                    new BigDecimal("420.139451035")
            );
            repository.save(bitcoin);
        };
    }


}