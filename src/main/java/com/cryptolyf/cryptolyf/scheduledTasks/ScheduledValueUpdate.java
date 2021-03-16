package com.cryptolyf.cryptolyf.scheduledTasks;

import com.cryptolyf.cryptolyf.model.Currency;
import com.cryptolyf.cryptolyf.repository.CurrencyRepository;
import com.cryptolyf.cryptolyf.service.BitfinexService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ScheduledValueUpdate {
    private final CurrencyRepository currencyRepository;
    private final BitfinexService bitfinexService;

    public ScheduledValueUpdate(CurrencyRepository currencyRepository, BitfinexService bitfinexService) {
        this.currencyRepository = currencyRepository;
        this.bitfinexService = bitfinexService;
    }

    @Scheduled(fixedRate = 10000)
    public void updateCurrencyValue() {
        List<Currency> currencies = currencyRepository.findAll();
        System.out.println("hello world");
        for (Currency i: currencies) {
            BigDecimal latestMarketValue = bitfinexService.getLastPrice(i.getName(), "EUR").multiply(i.getAmount());
            i.setValue(latestMarketValue);
            System.out.println(latestMarketValue);
            currencyRepository.save(i);
        }

    }

}
