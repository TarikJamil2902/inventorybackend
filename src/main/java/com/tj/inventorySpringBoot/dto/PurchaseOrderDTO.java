package com.tj.inventorySpringBoot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseOrderDTO {

    private Long purchaseOrderId;  // Corresponds to purchase_order_id in the entity
    private Long supplierId;  // Reference to the Supplier ID
    private List<Long> purchaseOrderItemIds;  // List of IDs of PurchaseOrderItems
    private LocalDateTime orderDate;  // Order date
    private LocalDateTime deliveryDate;  // New field for delivery date
    private String status;  // Status as a string (e.g., "PENDING", "RECEIVED")
    private Double totalAmount;  // Total amount for the order
    private String paymentTerms;  // Payment terms (e.g., "Net 30", "COD")


    // Getters and Setters

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<Long> getPurchaseOrderItemIds() {
        return purchaseOrderItemIds;
    }

    public void setPurchaseOrderItemIds(List<Long> purchaseOrderItemIds) {
        this.purchaseOrderItemIds = purchaseOrderItemIds;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
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


}
