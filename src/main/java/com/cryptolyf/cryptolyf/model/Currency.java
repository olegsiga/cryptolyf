package com.cryptolyf.cryptolyf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Currency {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal amount;
    private LocalDateTime created;
    private WalletType location;
    private BigDecimal value;

    public Currency() {
    }

    public Currency(String name, BigDecimal amount, LocalDateTime created, WalletType location, BigDecimal value) {
        this.name = name;
        this.amount = amount;
        this.created = created;
        this.location = location;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public WalletType getLocation() {
        return location;
    }

    public void setLocation(WalletType location) {
        this.location = location;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", doc=" + created +
                ", location='" + location + '\'' +
                ", price=" + value +
                '}';
    }
}
