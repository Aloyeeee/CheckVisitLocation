package com.checkvisitlocation.enums;

public enum ExportFormat {
    CSV,
    JSON,
    TXT;

    public String getExtension() {
        // Повертаємо розширення в нижньому регістрі, як стандарт для файлів
        return name().toLowerCase();
    }
}