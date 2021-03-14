package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping("/bitfinexPrice")
public class BitfinexPriceResource {
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public void calcFx(@RequestBody BitfinexCalcFxObject bitfinexCalcFxObject) {
        String url = "https://api-pub.bitfinex.com/v2/calc/fx";
        BigDecimal[] value = restTemplate.postForEntity(url, bitfinexCalcFxObject, BigDecimal[].class).getBody();
        //System.out.println(value[0]);
    }
}
