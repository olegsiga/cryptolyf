package com.cryptolyf.cryptolyf.currency;

import com.cryptolyf.cryptolyf.currency.model.Currency;
import com.cryptolyf.cryptolyf.currency.repository.CurrencyRepository;
import com.cryptolyf.cryptolyf.currency.service.CurrencyService;
import org.junit.Assert;
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
public class CurrencyServiceTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private CurrencyService currencyService;

    @Before
    public void setup() {
        Currency currency = new Currency();
        currency.setName("dogecoin");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation("Hardware Wallet");
        currency.setAmount(new BigDecimal("10"));
        currencyRepository.save(currency);
        System.out.println(currency.getId());
    }

//    @Test
//    public void findByNameTestReturnSuccess(){
//        Currency currency = currencyService.findByName("dogecoin");
//        Assert.assertNotNull(currency);
//    }

    @Test
    public void createCurrencySuccessfully(){
        //given
        Currency currency = new Currency();
        currency.setName("bitcoin2");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation("Hardware Wallet");
        currency.setAmount(new BigDecimal("10"));
        //when
        Currency savedCurrency  = currencyService.addCurrency(currency);
        //then
        Assert.assertNotNull(savedCurrency.getId());
    }

    @Test
    public void deleteCurrencySuccessfully(){
        //given
        Currency currency = new Currency();
        currency.setName("bitcoin3");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation("Hardware Wallet");
        currency.setAmount(new BigDecimal("10"));
        Currency savedCurrency2 = currencyService.addCurrency(currency);
        System.out.println("printing current currency ID: " + savedCurrency2.getId());
        //when
        currencyRepository.deleteById(savedCurrency2.getId());
        //then
        //Assert.assertNotNull(savedCurrency2.getId()); //passes test
        Assert.assertNull(savedCurrency2.getId());
    }

    @Test
    public void updateCurrencySuccessfully(){
        //given
        Currency currency = new Currency();
        currency.setName("bitcoin2");
        currency.setCreated(LocalDateTime.now());
        currency.setValue(new BigDecimal("234.234234"));
        currency.setLocation("Hardware Wallet");
        currency.setAmount(new BigDecimal("10"));
        //when
        Currency savedCurrency  = currencyService.updateCurrency(1l, "newlocation", new BigDecimal("3"));
        System.out.println("printing updateCurrencyLocationSuccessfully() ID: " + savedCurrency.getId());
        //then
        Assert.assertEquals(savedCurrency.getLocation(), "newlocation");
    }
}
