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
public class CryptolyfApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptolyfApplication.class, args);
	}
}
