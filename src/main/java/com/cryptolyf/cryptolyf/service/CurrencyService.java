package com.cryptolyf.cryptolyf.service;

import com.cryptolyf.cryptolyf.exceptions.CurrencyNotFoundException;
import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.model.CurrencyResource;
import com.cryptolyf.cryptolyf.model.WalletType;
import com.cryptolyf.cryptolyf.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final BitfinexService bitfinexService;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository,
                           BitfinexService bitfinexService) {
        this.currencyRepository = currencyRepository;
        this.bitfinexService = bitfinexService;
    }

    public List<CurrencyResource> getCurrencies() {
        List<CurrencyResource> currencyResources = new ArrayList<>();
        List<Currency> currencies = currencyRepository.findAll();
        for (Currency currency : currencies) {
            currencyResources.add(
                    new CurrencyResource()
                            .setId(currency.getId())
                            .setName(currency.getName())
                            .setValue(currency.getValue())
                            .setAmount(currency.getAmount())
                            .setCreated(currency.getCreated())
                            .setLocation(currency.getLocation())

            );
        }
        return currencyResources;
    }

    public CurrencyResource findById(Long id) throws CurrencyNotFoundException {
        boolean exists = currencyRepository.existsById(id);
        if (!exists) {
            throw new CurrencyNotFoundException("the id: " + id + " doesn't exist");
        } else {
            Optional<Currency> currency;
            currency = currencyRepository.findById(id);
            return new CurrencyResource()
                    .setId(currency.get().getId())
                    .setName(currency.get().getName())
                    .setValue(currency.get().getValue())
                    .setAmount(currency.get().getAmount())
                    .setCreated(currency.get().getCreated())
                    .setLocation(currency.get().getLocation());
        }
    }

    public void deleteCurrency(Long id) throws CurrencyNotFoundException {
        boolean exists = currencyRepository.existsById(id);
        if (!exists) {
            throw new CurrencyNotFoundException("the id: " + id + " doesn't exist");
        } else {
            currencyRepository.deleteById(id);
        }
    }

    @Transactional
    public Currency updateCurrency(Long id,
                                   WalletType location,
                                   BigDecimal amount) throws CurrencyNotFoundException {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new CurrencyNotFoundException("the id: " + id + " not found"));

        if (location != null && location.toString().length() > 0
                && !Objects.equals(currency.getLocation(), location)) {
            currency.setLocation(location);
        } else {
            currency.setLocation(currency.getLocation());
        }

        if (amount != null && amount.intValue() >= 0
                && !Objects.equals(currency.getAmount(), amount)) {
            currency.setAmount(amount);
            BigDecimal calculatedValue = currency.getAmount()
                    .multiply(bitfinexService.getLastPrice(currency.getName()));
            currency.setValue(calculatedValue);
        } else {
            currency.setValue(currency.getValue());
        }
        currencyRepository.save(currency);
        return currency;
    }

    public Currency addCurrency(Currency currency) {
        BigDecimal calculatedValue = currency.getAmount()
                .multiply(bitfinexService.getLastPrice(currency.getName()));
        currency.setValue(calculatedValue);
        currency.setCreated(LocalDateTime.now());
        currency.setLocation(currency.getLocation());
        currencyRepository.save(currency);
        return currency;
    }
}
