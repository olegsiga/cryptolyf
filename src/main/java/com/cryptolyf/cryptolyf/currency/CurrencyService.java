package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final BitfinexResource bitfinexResource;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, BitfinexResource bitfinexResource) {
        this.currencyRepository = currencyRepository;
        this.bitfinexResource = bitfinexResource;
    }

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency findByName(String name) {
        Currency currency = currencyRepository.findByName(name);
        currency.setValue(currency.getAmount().multiply(bitfinexResource.getOne()));
        return currencyRepository.findByName(name);
    }

    public void deleteCurrency(Long id) {
        boolean exists = currencyRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("the id: " + id + " doesn't exist");
        } else {
            currencyRepository.deleteById(id);
            System.out.println("deleted");
        }
    }

    @Transactional
    public Currency updateCurrency(Long id,
                                   String location,
                                   BigDecimal amount) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "currency " + id + " not found"));

        if (location.length() > 0 && !Objects.equals(currency.getLocation(), location)) {
            currency.setLocation(location);
        }
        if (amount.intValue() > 0 && !Objects.equals(currency.getAmount(), amount)) {
            currency.setAmount(amount);
            currency.setValue(amount.multiply(bitfinexResource.getOne()));
        }
        return currency;
    }

    public Currency addCurrency(Currency currency) {
        Optional<Currency> currencyOptional = Optional
                .ofNullable(currencyRepository
                        .findByName(currency.getName()));
        if (currencyOptional.isPresent()) {
            throw new IllegalStateException("currency already exists");
        }

        BigDecimal calculatedValue = currency.getAmount()
                .multiply(bitfinexResource.getOne());
        currency.setValue(calculatedValue);
        currency.setCreated(LocalDateTime.now());
        currencyRepository.save(currency);
        return currency;
    }
}
