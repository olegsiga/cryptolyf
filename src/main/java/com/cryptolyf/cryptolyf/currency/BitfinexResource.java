package com.cryptolyf.cryptolyf.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bitfinex")
public class BitfinexResource {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/currency")
    public Optional<Object> getPrice() {
        String url = "https://api-pub.bitfinex.com/v2/ticker/tBTCEUR";
        Object[] objects = restTemplate.getForObject(url, Object[].class);

        return Arrays.stream(objects).findFirst();
    }
//    @GetMapping("/currency")
//    public List<Object> getPrice() {
//        String url = "https://api-pub.bitfinex.com/v2/ticker/tBTCEUR";
//        Object[] objects = restTemplate.getForObject(url, Object[].class);
//
//        return Arrays.asList(Arrays.stream(objects).findFirst());
//    }


    public BigDecimal getOne() {
        System.out.println("printing getOne " + getPrice().get().toString());
        return new BigDecimal(getPrice().get().toString());
    }

}
