package com.tj.inventorySpringBoot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportDTO {

    private Long reportId;  // Report ID
    private String reportType;  // e.g., "sales", "stock", "financial"
    private LocalDate startDate;  // Start date for the report period
    private LocalDate endDate;  // End date for the report period
    private LocalDateTime generatedAt;  // Timestamp when the report was generated
    private String generatedByEmployeeId;  // ID of the Employee who generated the report
    private String data;  // Report content in JSON or another format
    private String createdByUserName;  // Username of the user who created the report

    // No `createdTime` field, as it's not required in the DTO

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getGeneratedByEmployeeId() {
        return generatedByEmployeeId;
    }

    public void setGeneratedByEmployeeId(String generatedByEmployeeId) {
        this.generatedByEmployeeId = generatedByEmployeeId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByUserName) {
        this.createdByUserName = createdByUserName;
    }

    // Getters and setters
}
