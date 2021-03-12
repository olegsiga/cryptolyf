package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/bitfinex")
public class BitfinexResource {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency")
    public List<Object> getCurrencies() {

        String url = "https://api-pub.bitfinex.com/v2/ticker/tBTCEUR";
        Object[] objects = restTemplate.getForObject(url, Object[].class);

        return Arrays.asList(objects);
    }
}
