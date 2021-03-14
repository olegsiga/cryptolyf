package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitfinexPrice")
public class BitfinexPriceResource {
    @Autowired
    private RestTemplate restTemplate;


}
