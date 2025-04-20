package com.checkvisitlocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.checkvisitlocation")
public class CheckVisitLocationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckVisitLocationApplication.class, args);
    }

}