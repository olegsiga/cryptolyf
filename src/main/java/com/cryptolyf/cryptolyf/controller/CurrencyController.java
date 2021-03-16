package com.cryptolyf.cryptolyf.controller;

import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.model.CurrencyResource;
import com.cryptolyf.cryptolyf.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/currency/findAll")
    public List<CurrencyResource> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping(value = "/currency/findOne/{id}")
    public CurrencyResource findById(@PathVariable("id") Long id) {
        return currencyService.findById(id);
    }

    @PostMapping(value = "/currency/add")
    public void addCurrency(@RequestBody Currency currency) {
        currencyService.addCurrency(currency);
    }

    @DeleteMapping(path = "/currency/delete/{id}")
    public void deleteCurrency(@PathVariable("id") Long id) {
        currencyService.deleteCurrency(id);
    }

    @PutMapping(path = "/currency/update/{id}")
    public void updateCurrency(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) BigDecimal amount) {
        currencyService.updateCurrency(id, location, amount);
    }

}
