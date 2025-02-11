package com.tj.inventorySpringBoot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderDTO {

    private Long purchaseOrderId;  // Corresponds to purchase_order_id in the entity
    private String supplierId;  // Reference to the Supplier ID
    private String purchaseOrderItems;
    // List of IDs of PurchaseOrderItems
    private LocalDate orderDate;  // Order date
    private LocalDate deliveryDate;  // New field for delivery date
    private String status;  // Status as a string (e.g., "PENDING", "RECEIVED")
    private Double totalAmount;  // Total amount for the order
    private String paymentTerms;  // Payment terms (e.g., "Net 30", "COD")
    private String createdBy;

    // Getters and Setters

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPurchaseOrderItems() {
        return purchaseOrderItems;
    }

    public void setPurchaseOrderItems(String purchaseOrderItems) {
        this.purchaseOrderItems = purchaseOrderItems;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
