package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

public interface ExportStrategy {
    String export(List<Visit> visits);
    String getFileExtension();
}