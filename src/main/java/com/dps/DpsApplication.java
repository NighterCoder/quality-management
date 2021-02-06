package com.dps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DpsApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DpsApplication.class, args);
    }

}
