package com.checkvisitlocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Головний клас додатку CheckVisitLocation.
 * Цей клас є точкою входу для додатку, який відповідає за перевірку відвідування локацій.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@SpringBootApplication
public class CheckVisitLocationApplication {
    /**
     * Головний метод, який запускає Spring Boot додаток.
     * 
     * @param args аргументи командного рядка, які передаються при запуску додатку
     */
    public static void main(String[] args) {
        SpringApplication.run(CheckVisitLocationApplication.class, args);
    }
}