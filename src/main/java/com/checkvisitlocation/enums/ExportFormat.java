package com.checkvisitlocation.enums;

public enum ExportFormat {
    CSV,
    JSON,
    TEXT;

    public String getExtension() {
        return name().toLowerCase();
    }
}