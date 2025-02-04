package com.tj.inventorySpringBoot.dto;

public class SupplierDTO {

    private Long supplierId;
    private String name;

    private String email; // Updated field
    private String phone; // Updated field
    private String address;
    private String paymentTerms; // New field
    private Double supplierRating; // New field
    private String status; // New field

    // Excluding products, createdTime, and updatedTime from the DTO


    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public Double getSupplierRating() {
        return supplierRating;
    }

    public void setSupplierRating(Double supplierRating) {
        this.supplierRating = supplierRating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
