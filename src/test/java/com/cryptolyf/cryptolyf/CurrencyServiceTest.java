package com.cryptolyf.cryptolyf;

import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.model.WalletType;
import com.cryptolyf.cryptolyf.repository.CurrencyRepository;
import com.cryptolyf.cryptolyf.service.CurrencyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyService currencyService;

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
    public void createCurrencySuccessfully() {
        //given
        Currency currency = new Currency();
        currency.setName("BTC");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation(WalletType.HARDWARE);
        currency.setAmount(new BigDecimal("10"));
        //when
        Currency savedCurrency = currencyService.addCurrency(currency);
        //then
        Assert.assertNotNull(savedCurrency.getId());
    }

    @Test
    public void deleteCurrencySuccessfully() {
        //given
        Currency currency = new Currency();
        currency.setName("BTC");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation(WalletType.HARDWARE);
        currency.setAmount(new BigDecimal("10"));
        Currency savedCurrency2 = currencyService.addCurrency(currency);
        //when
        currencyRepository.deleteById(savedCurrency2.getId());
        //then
        final Optional<Currency> deletedEntry = currencyRepository.findById(savedCurrency2.getId());
        Assert.assertTrue(deletedEntry.isEmpty());
    }

    @Test
    public void updateCurrencySuccessfully() {
        //given
        Currency currency = new Currency();
        currency.setName("BTC");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation(WalletType.HARDWARE);
        currency.setAmount(new BigDecimal("10"));
        //when
        Currency savedCurrency = currencyService.updateCurrency(1l, WalletType.DESKTOP, new BigDecimal("3"));
        System.out.println("printing updateCurrencyLocationSuccessfully() ID: " + savedCurrency.getId());
        //then
        Assert.assertEquals(savedCurrency.getLocation(), "newlocation");
    }
}
