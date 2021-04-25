package com.cryptolyf.cryptolyf.model;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private Long accountNumber;
    private LocalDateTime created;
    private String currencyType;
    private String amount;
}
