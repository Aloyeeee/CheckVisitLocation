package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;

public interface DataExportVisitor {
    String visit(Visit visit);
    String visit(AnalyticsResponse analytics);
}