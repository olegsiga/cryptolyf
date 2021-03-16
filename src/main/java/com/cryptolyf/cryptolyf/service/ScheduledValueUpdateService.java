package com.cryptolyf.cryptolyf.service;

import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.repository.CurrencyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ScheduledValueUpdateService {
    private final CurrencyRepository currencyRepository;
    private final BitfinexService bitfinexService;

    public ScheduledValueUpdateService(CurrencyRepository currencyRepository, BitfinexService bitfinexService) {
        this.currencyRepository = currencyRepository;
        this.bitfinexService = bitfinexService;
    }

    @Scheduled(fixedRate = 60000)
    public void updateCurrencyValue() {
        List<Currency> currencies = currencyRepository.findAll();
        for (Currency i : currencies) {
            BigDecimal latestMarketValue = bitfinexService.getLastPrice(i.getName()).multiply(i.getAmount());
            i.setValue(latestMarketValue);
            currencyRepository.save(i);
        }

    }

}
