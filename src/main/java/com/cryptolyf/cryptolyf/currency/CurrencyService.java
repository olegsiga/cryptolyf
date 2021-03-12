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

    //not finished
    @Transactional
    public void updateCurrency(Long id, String name, BigDecimal amount, String location, BigDecimal price) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "currency " + id + " not found"));

        if (name.length() > 0 && !Objects.equals(currency.getName(), name)) {
            currency.setName(name);
        }
    }

    public void addCurrency(Currency currency) {
        Optional<Currency> currencyOptional = Optional.ofNullable(currencyRepository
                .findByName(currency.getName()));
        if (currencyOptional.isPresent()) {
            throw new IllegalStateException("currency already exists");
        }
        BigDecimal calculatedPrice = currency.getAmount().multiply(bitfinexResource.getOne());
        currency.setPrice(calculatedPrice);
        currency.setCreated(LocalDateTime.now());
        currencyRepository.save(currency);
    }
}
