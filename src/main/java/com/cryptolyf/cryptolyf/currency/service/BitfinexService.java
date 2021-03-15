package com.cryptolyf.cryptolyf.currency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

//This class is not ready to use
@Service
public class BitfinexService {
    @Autowired
    private RestTemplate restTemplate;

    public BigDecimal getLastPrice(String ccy1, String ccy2) {
        String url = "https://api-pub.bitfinex.com/v2/calc/fx";
        Map<String, String> params = new HashMap<>();
        params.put("ccy1",ccy1);
        params.put("ccy2",ccy2);
        BigDecimal[] value = restTemplate.postForEntity(url, params, BigDecimal[].class).getBody();
        //System.out.println(value[0]);
        return value[0];
    }
}
