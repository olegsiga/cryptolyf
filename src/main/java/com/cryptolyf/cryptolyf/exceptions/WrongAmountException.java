package com.cryptolyf.cryptolyf.exceptions;

public class WrongAmountException extends Throwable {
    public WrongAmountException(String msg) {
        System.out.println(msg);
    }
}
