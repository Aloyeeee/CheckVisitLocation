package com.checkvisitlocation.enums;

public enum ExportFormat {
    CSV,
    JSON,
    TEXT;

    public String getExtension() {
        // Повертаємо розширення в нижньому регістрі, як стандарт для файлів
        return name().toLowerCase();
    }
}