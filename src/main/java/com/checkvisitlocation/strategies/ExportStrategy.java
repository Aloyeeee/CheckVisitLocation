package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

/**
 * Інтерфейс стратегії експорту відвідувань у різні формати.
 */
public interface ExportStrategy {
    /**
     * Експортує список відвідувань у відповідному форматі.
     * @param visits список відвідувань
     * @return рядок з експортованими даними
     */
    String export(List<Visit> visits);
    /**
     * Повертає розширення файлу для експорту.
     * @return розширення файлу (наприклад, "csv", "json", "txt")
     */
    String getFileExtension();
}