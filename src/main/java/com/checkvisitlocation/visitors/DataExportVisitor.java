package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;

/**
 * Інтерфейс відвідувача для експорту різних типів даних.
 * Дозволяє експортувати Visit та AnalyticsResponse у різних форматах.
 */
public interface DataExportVisitor {
    /**
     * Експортує об'єкт Visit у відповідному форматі.
     * @param visit відвідування для експорту
     * @return рядок з експортованими даними
     */
    String visit(Visit visit);
    /**
     * Експортує об'єкт AnalyticsResponse у відповідному форматі.
     * @param analytics аналітична відповідь для експорту
     * @return рядок з експортованими даними
     */
    String visit(AnalyticsResponse analytics);
}