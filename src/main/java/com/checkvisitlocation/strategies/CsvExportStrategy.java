package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

public class CsvExportStrategy implements ExportStrategy {
    @Override
    public String export(List<Visit> visits) {
        StringBuilder sb = new StringBuilder();
        sb.append("Location,Visit Date,Rating,Impressions\n");

        for (Visit visit : visits) {
            sb.append(escapeCsv(visit.getLocation().getName())).append(",")
                    .append(visit.getVisitDate()).append(",")
                    .append(visit.getRating()).append(",")
                    .append(escapeCsv(visit.getImpressions())).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String getFileExtension() {
        return "csv";
    }

    private String escapeCsv(String input) {
        if (input == null) return "";
        return "\"" + input.replace("\"", "\"\"") + "\"";
    }
}