package com.tj.inventorySpringBoot.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "returns")
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "return_id")
    private Long returnId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = true)
    private Order customerOrder; // If it's a customer return

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = true)
    private PurchaseOrder supplierReturn; // If it's a supplier return

    @Column(name = "reason_for_return")
    private String reason; // Reason for the return (e.g., defective, wrong item)

    @Column(name = "return_date")
    private LocalDateTime returnDate; // Date when the return was requested or processed

    @Column(name = "return_status")
    private String returnStatus; // Status of the return (e.g., pending, processed)

    @Column(name = "amount_refunded")
    private Double amountRefunded; // Amount refunded for the return

    @Column(name = "return_type")
    private String returnType; // Type of the return (e.g., full, partial)

    @Column(name = "refund_method")
    private String refundMethod; // Method of refund (e.g., store credit, original payment)
    private Integer quantity;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = LocalDateTime.now();
    }

    // Getters and Setters


    public Long getReturnId() {
        return returnId;
    }

    public void setReturnId(Long returnId) {
        this.returnId = returnId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customerOrder = customerOrder;
    }

    public PurchaseOrder getSupplierReturn() {
        return supplierReturn;
    }

    public void setSupplierReturn(PurchaseOrder supplierReturn) {
        this.supplierReturn = supplierReturn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    // Getters and setters
}
