package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "api/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<Currency> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @GetMapping(value = "{name}")
    public Currency findByName(@PathVariable("name") String name){
        return currencyService.findByName(name);
    }

    @PostMapping
    public void addCurrency(@RequestBody Currency currency) {
        currencyService.addCurrency(currency);
    }

    @DeleteMapping(path = "{id}")
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
