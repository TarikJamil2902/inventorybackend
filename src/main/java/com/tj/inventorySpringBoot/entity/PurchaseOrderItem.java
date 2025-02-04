package com.tj.inventorySpringBoot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    private Double costPerUnit;

    private Double discount; // Discount on the item (if any)

    private Double taxRate; // Tax rate applicable to the item (percentage)

    private Double totalCost; // Total cost for this item (quantity * costPerUnit)

    private Double totalCostWithTax; // Total cost including tax

    private Double totalCostAfterDiscount; // Total cost after discount

    private String status; // Status of the item (e.g., "ACTIVE", "RETURNED", etc.)

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();

        // Calculate total costs during creation
        calculateTotalCosts();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedTime = LocalDateTime.now();
        // Recalculate total costs during update
        calculateTotalCosts();
    }

    private void calculateTotalCosts() {
        if (quantity != null && costPerUnit != null) {
            // Total cost without discount and tax
            this.totalCost = quantity * costPerUnit;

            // Apply discount if available
            if (discount != null && discount > 0) {
                this.totalCostAfterDiscount = totalCost - (totalCost * discount / 100);
            } else {
                this.totalCostAfterDiscount = totalCost;
            }

            // Apply tax if applicable
            if (taxRate != null && taxRate > 0) {
                this.totalCostWithTax = totalCostAfterDiscount + (totalCostAfterDiscount * taxRate / 100);
            } else {
                this.totalCostWithTax = totalCostAfterDiscount;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateTotalCosts(); // Recalculate totals when quantity changes
    }

    public Double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
        this.costPerUnit = costPerUnit;
        calculateTotalCosts(); // Recalculate totals when costPerUnit changes
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
        calculateTotalCosts(); // Recalculate totals when discount changes
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
        calculateTotalCosts(); // Recalculate totals when tax rate changes
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalCostWithTax() {
        return totalCostWithTax;
    }

    public void setTotalCostWithTax(Double totalCostWithTax) {
        this.totalCostWithTax = totalCostWithTax;
    }

    public Double getTotalCostAfterDiscount() {
        return totalCostAfterDiscount;
    }

    public void setTotalCostAfterDiscount(Double totalCostAfterDiscount) {
        this.totalCostAfterDiscount = totalCostAfterDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
