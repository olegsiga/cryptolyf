package com.cryptolyf.cryptolyf.exceptions;

public class CurrencyNotFoundException extends Throwable{
    public CurrencyNotFoundException(String msg) {
        System.out.println(msg);
    }
}
