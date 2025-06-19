package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

/**
 * Інтерфейс, що визначає стратегію експорту даних про відвідування.
 * Реалізує патерн Стратегія для підтримки різних форматів експорту.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public interface ExportStrategy {
    /**
     * Експортує список відвідувань у відповідному форматі.
     * 
     * @param visits список відвідувань для експорту
     * @return рядок, що містить експортовані дані у відповідному форматі
     */
    String export(List<Visit> visits);

    /**
     * Отримує розширення файлу для поточного формату експорту.
     * 
     * @return розширення файлу (наприклад, "csv", "json", "txt")
     */
    String getFileExtension();
}