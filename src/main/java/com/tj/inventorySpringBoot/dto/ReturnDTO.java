package com.tj.inventorySpringBoot.dto;

import java.time.LocalDateTime;

public class ReturnDTO {

    private Long returnId;
    private Long customerOrderId; // ID of the associated customer order (if applicable)
    private Long supplierReturnId; // ID of the associated supplier return (if applicable)
    private String reasonForReturn; // Reason for the return (e.g., defective, wrong item)
    private LocalDateTime returnDate; // Date of the return
    private String returnStatus; // Status of the return (e.g., pending, processed)
    private Double amountRefunded; // Amount refunded for the return
    private String returnType; // Type of the return (e.g., full, partial)
    private String refundMethod; // Method of refund (e.g., store credit, original payment)
    private Integer quantity;

    // Excluding createdTime and updatedTime from the DTO

    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Long getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Long customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public Long getSupplierReturnId() {
        return supplierReturnId;
    }

    public void setSupplierReturnId(Long supplierReturnId) {
        this.supplierReturnId = supplierReturnId;
    }

    public String getReasonForReturn() {
        return reasonForReturn;
    }

    public void setReasonForReturn(String reasonForReturn) {
        this.reasonForReturn = reasonForReturn;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Double getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(Double amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(String refundMethod) {
        this.refundMethod = refundMethod;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getters and setters
}
