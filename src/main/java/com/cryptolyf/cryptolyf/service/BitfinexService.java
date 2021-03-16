package com.cryptolyf.cryptolyf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BitfinexService {
    private RestTemplate restTemplate;

    public BitfinexService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal getLastPrice(String ccy1) {
        String url = "https://api-pub.bitfinex.com/v2/calc/fx";
        Map<String, String> params = new HashMap<>();
        params.put("ccy1", ccy1);
        params.put("ccy2", "EUR");
        BigDecimal[] value = restTemplate.postForEntity(url, params, BigDecimal[].class).getBody();
        return value != null ? value[0] : null;
    }
}
