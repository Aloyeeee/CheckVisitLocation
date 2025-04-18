package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JsonExportStrategy implements ExportStrategy {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String export(List<Visit> visits) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(visits);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
        }
    }

    @Override
    public String getFileExtension() {
        return "json";
    }
}