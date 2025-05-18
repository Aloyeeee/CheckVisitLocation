package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonExportVisitor implements DataExportVisitor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String visit(Visit visit) {
        try {
            return objectMapper.writeValueAsString(visit);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
        }
    }

    @Override
    public String visit(AnalyticsResponse analytics) {
        try {
            return objectMapper.writeValueAsString(analytics);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
        }
    }
}