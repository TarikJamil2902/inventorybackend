package com.tj.inventorySpringBoot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;  // Renamed to `reportId` as primary key

    private String reportType;  // e.g., "sales", "stock", "financial"

    private LocalDate startDate;  // Start date for the report period

    private LocalDate endDate;  // End date for the report period

    private LocalDateTime generatedAt;  // Timestamp when the report was generated

    @ManyToOne
    @JoinColumn  // Linking to Employee entity (assuming Employee entity exists)
    private Employee generatedBy;

    @Lob  // Large object, assuming the content is stored as JSON or other formats
    private String data;  // Report content in JSON or another format

    @ManyToOne
    @JoinColumn // Link to the User entity for the creator
    private User createdBy;

    private LocalDateTime createdTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
        this.generatedAt = LocalDateTime.now();  // Set the timestamp when the report is created
    }

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

    public Employee getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(Employee generatedBy) {
        this.generatedBy = generatedBy;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }


}
