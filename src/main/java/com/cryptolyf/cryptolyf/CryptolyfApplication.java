package com.cryptolyf.cryptolyf;

import com.cryptolyf.cryptolyf.currency.Currency;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class CryptolyfApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptolyfApplication.class, args);
	}

	@GetMapping
	public List<Currency> hello() {
		return List.of(
				new Currency(
						1L,
						"bitcoin",
						new BigDecimal("0.00001"),
						LocalDateTime.of(2021, Month.MARCH, 8, 14, 5, 5),
						"Hardware Wallet",
						new BigDecimal("2232.03141")
				)
		);
	}
}
