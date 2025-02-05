package com.tj.inventorySpringBoot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaxDTO {

    private Long taxId;
    private String taxType; // e.g., "GST", "VAT"
    private Double rate;
    private String description; // Description of the tax
    private String taxCategory; // Category of the tax (e.g., "Sales", "Income")
    private Boolean isActive; // Whether the tax is currently active
    private LocalDate effectiveFrom; // Start date for the tax rate
    private LocalDate effectiveTill; // End date for the tax rate (optional)

    // Excluding orders, createdTime, and updatedTime from the DTO


    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTill() {
        return effectiveTill;
    }

    public void setEffectiveTill(LocalDate effectiveTill) {
        this.effectiveTill = effectiveTill;
    }

    // Getters and setters
}
