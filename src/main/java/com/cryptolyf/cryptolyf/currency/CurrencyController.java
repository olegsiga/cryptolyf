package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/currency/list")
    public List<CurrencyResource> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping(value = "/currency/find/{id}")
    public Optional<Currency> findById(@PathVariable("id") Long id){
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

    @PutMapping(path = "{id}")
    public void updateCurrency(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) BigDecimal amount) {
        currencyService.updateCurrency(id,location, amount);
    }

}
