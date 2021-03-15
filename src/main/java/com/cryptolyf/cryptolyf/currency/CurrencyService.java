package com.cryptolyf.cryptolyf.currency;

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
    private final BitfinexResource bitfinexResource;
    private final BitfinexService bitfinexService;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, BitfinexResource bitfinexResource, BitfinexService bitfinexService) {
        this.currencyRepository = currencyRepository;
        this.bitfinexResource = bitfinexResource;
        this.bitfinexService = bitfinexService;
    }
//check this

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


        }return currencyResources;
    }

    public Optional<Currency> findById(Long id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        //currency.setValue(currency.getAmount().multiply(bitfinexResource.getOne()));
        return currencyRepository.findById(id);
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
            BigDecimal calculatedValue = currency.getAmount()
                    .multiply(bitfinexService.getLastPrice(currency.getName(), "EUR"));
            currency.setValue(calculatedValue);
            currency.setCreated(LocalDateTime.now());
            currencyRepository.save(currency);
            System.out.println("Your currency " + currency.getName() + " was added, current value is: " + calculatedValue);
            return currency;
        }

}
