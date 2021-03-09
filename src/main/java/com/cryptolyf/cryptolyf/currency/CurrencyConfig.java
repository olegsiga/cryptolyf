package com.cryptolyf.cryptolyf.currency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.Month.MARCH;

@Configuration
public class CurrencyConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            CurrencyRepository repository) {
        return args -> {
            Currency bitcoin = new Currency(
                    "bitcoin",
                    new BigDecimal("0.1337"),
                    LocalDateTime.of(2021, MARCH, 10, 14, 11, 11),
                    "Hardware Wallet",
                    new BigDecimal("420.139451035")
                    );
            repository.save(bitcoin);
        };
    }

}
