package com.checkvisitlocation.enums;

/**
 * Формати експорту даних (CSV, JSON, TXT).
 */
public enum ExportFormat {
    CSV,
    JSON,
    TXT;

    /**
     * Повертає розширення файлу для формату експорту у нижньому регістрі.
     * @return розширення файлу (csv, json, txt)
     */
    public String getExtension() {
        // Повертаємо розширення в нижньому регістрі, як стандарт для файлів
        return name().toLowerCase();
    }
}