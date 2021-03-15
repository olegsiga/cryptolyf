package com.cryptolyf.cryptolyf.currency.repository;

import com.cryptolyf.cryptolyf.currency.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
