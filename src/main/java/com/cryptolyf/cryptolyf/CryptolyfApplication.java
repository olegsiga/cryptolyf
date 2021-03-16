package com.cryptolyf.cryptolyf;

import com.cryptolyf.cryptolyf.currency.scheduledTasks.ScheduledValueUpdate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class CryptolyfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptolyfApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}


