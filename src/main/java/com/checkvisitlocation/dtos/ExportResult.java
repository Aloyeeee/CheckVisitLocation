package com.checkvisitlocation.dtos;

/**
 * DTO для результату експорту (файл та його вміст).
 */
public class ExportResult {
    /** Ім'я файлу експорту. */
    private String filename;
    /** Вміст файлу експорту. */
    private String content;

    /**
     * Конструктор з іменем файлу та вмістом.
     * @param filename ім'я файлу
     * @param content вміст файлу
     */
    public ExportResult(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    /** Повертає ім'я файлу експорту. */
    public String getFilename() {
        return filename;
    }

    /** Встановлює ім'я файлу експорту. */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /** Повертає вміст файлу експорту. */
    public String getContent() {
        return content;
    }

    /** Встановлює вміст файлу експорту. */
    public void setContent(String content) {
        this.content = content;
    }
}