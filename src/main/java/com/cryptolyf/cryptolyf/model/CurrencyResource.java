package com.cryptolyf.cryptolyf.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrencyResource {

    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDateTime created;
    private WalletType location;
    private BigDecimal value;

    public Long getId() {
        return id;
    }

    public CurrencyResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CurrencyResource setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyResource setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CurrencyResource setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public WalletType getLocation() {
        return location;
    }

    public CurrencyResource setLocation(WalletType location) {
        this.location = location;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public CurrencyResource setValue(BigDecimal value) {
        this.value = value;
        return this;
    }
}
