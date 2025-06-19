package com.checkvisitlocation.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

/**
 * Конфігурація локалізації для додатку.
 * Налаштовує підтримку мов та джерело повідомлень.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Configuration
public class LocaleConfig {

    /**
     * Створює резолвер локалі.
     * Налаштовує підтримку англійської та української мов.
     * 
     * @return налаштований резолвер локалі
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(List.of(Locale.ENGLISH, Locale.of("uk", "UA")));
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }

    /**
     * Створює джерело повідомлень.
     * Налаштовує завантаження повідомлень з файлів ресурсів.
     * 
     * @return налаштоване джерело повідомлень
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}