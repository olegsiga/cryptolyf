package com.cryptolyf.cryptolyf.exceptions;

public class WrongAmountException extends Exception {
    public WrongAmountException(String msg) {
        System.out.println(msg);
    }
}
