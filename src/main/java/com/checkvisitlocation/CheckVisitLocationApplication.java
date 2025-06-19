package com.checkvisitlocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Головний клас Spring Boot застосунку CheckVisitLocation.
 * Запускає додаток.
 */
@SpringBootApplication
public class CheckVisitLocationApplication {
    /**
     * Точка входу в застосунок.
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        SpringApplication.run(CheckVisitLocationApplication.class, args);
    }
}