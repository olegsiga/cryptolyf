package com.cryptolyf.cryptolyf.currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Currency {
    //public Currency(){}
//    @Id
//    @SequenceGenerator(
//            name = "currency_sequence",
//            sequenceName = "currency_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "currency_sequence"
//    )
    @Id
    @GeneratedValue
    private Long id ;
    private String name;
    private BigDecimal amount;
    private LocalDateTime created;
    private String location;
    private BigDecimal price;

    public Currency(Long id, String name, BigDecimal amount, LocalDateTime created, String location, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.created = created;
        this.location = location;
        this.price = price;
    }

    public Currency(String name, BigDecimal amount, LocalDateTime created, String location, BigDecimal price) {
        this.name = name;
        this.amount = amount;
        this.created = created;
        this.location = location;
        this.price = price;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", doc=" + created +
                ", location='" + location + '\'' +
                ", price=" + price +
                '}';
    }
}
