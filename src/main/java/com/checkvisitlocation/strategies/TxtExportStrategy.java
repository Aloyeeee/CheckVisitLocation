package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

public class TxtExportStrategy implements ExportStrategy {
    @Override
    public String export(List<Visit> visits) {
        StringBuilder sb = new StringBuilder();
        sb.append("Visited Locations Report\n\n");

        for (Visit visit : visits) {
            sb.append("Location: ").append(visit.getLocation().getName()).append("\n")
                    .append("Date: ").append(visit.getVisitDate()).append("\n")
                    .append("Rating: ").append(visit.getRating()).append("/5\n")
                    .append("Impressions: ").append(visit.getImpressions()).append("\n\n");
        }

        return sb.toString();
    }

    @Override
    public String getFileExtension() {
        return "txt";
    }
}