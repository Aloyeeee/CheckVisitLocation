package com.checkvisitlocation.enums;

/**
 * Перелік форматів експорту даних.
 * Визначає підтримувані формати для експорту аналітичних даних.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public enum ExportFormat {
    /** Формат CSV (Comma-Separated Values) */
    CSV,
    
    /** Формат JSON (JavaScript Object Notation) */
    JSON,
    
    /** Формат TXT (текстовий файл) */
    TXT;

    /**
     * Отримує розширення файлу для поточного формату.
     * 
     * @return розширення файлу в нижньому регістрі
     */
    public String getExtension() {
        // Повертаємо розширення в нижньому регістрі, як стандарт для файлів
        return name().toLowerCase();
    }
}