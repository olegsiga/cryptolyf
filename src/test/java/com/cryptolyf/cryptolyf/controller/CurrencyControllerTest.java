package com.cryptolyf.cryptolyf.controller;

import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.model.WalletType;
import com.cryptolyf.cryptolyf.repository.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyControllerTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyController currencyController;
    @Before
    public void setup() {
        Currency currency = new Currency();
        currency.setName("LTC");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation(WalletType.HARDWARE);
        currency.setAmount(new BigDecimal("10"));
        currencyRepository.save(currency);
    }

    @Test
    public void findOneCurrencySuccessfully(){

    }
}

