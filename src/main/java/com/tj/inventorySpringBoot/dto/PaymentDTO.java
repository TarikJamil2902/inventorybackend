package com.tj.inventorySpringBoot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentDTO {

    private Long paymentId;
    private String orderId;       // Reference to the Order ID
    private String customerId;   // Reference to the Customer ID
    private Double amount;
    private String paymentMethod;  // e.g., "Credit Card", "PayPal"
    private String paymentStatus;  // e.g., "COMPLETED", "PENDING", "REFUNDED"
    private String currency;      // Currency used for the payment
    private String transactionId; // For payment gateway tracking
    private LocalDate paymentDate;   // Payment date as String (e.g., "YYYY-MM-DD HH:mm:ss")

    // No `createdTime`, `updatedTime` fields


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Getters and setters

}
