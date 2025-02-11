package com.tj.inventorySpringBoot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReturnDTO {

    private Long returnId;
    private String customerOrderId; // ID of the associated customer order (if applicable)
    private String reasonForReturn; // Reason for the return (e.g., defective, wrong item)
    private LocalDate returnDate; // Date of the return
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


    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getReasonForReturn() {
        return reasonForReturn;
    }

    public void setReasonForReturn(String reasonForReturn) {
        this.reasonForReturn = reasonForReturn;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
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
