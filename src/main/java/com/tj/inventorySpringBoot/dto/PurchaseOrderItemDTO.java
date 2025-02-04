package com.tj.inventorySpringBoot.dto;

public class PurchaseOrderItemDTO {

    private Long id;
    private Long purchaseOrderId;  // Reference to the PurchaseOrder ID
    private Long productId;  // Reference to the Product ID
    private Integer quantity;
    private Double costPerUnit;
    private Double discount;  // Discount on the item
    private Double taxRate;  // Tax rate applicable to the item
    private Double totalCost;  // Total cost without discount and tax
    private Double totalCostAfterDiscount;  // Total cost after discount
    private Double totalCostWithTax;  // Total cost with tax included

    // No `createdTime`, `updatedTime` fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getTotalCostAfterDiscount() {
        return totalCostAfterDiscount;
    }

    public void setTotalCostAfterDiscount(Double totalCostAfterDiscount) {
        this.totalCostAfterDiscount = totalCostAfterDiscount;
    }

    public Double getTotalCostWithTax() {
        return totalCostWithTax;
    }

    public void setTotalCostWithTax(Double totalCostWithTax) {
        this.totalCostWithTax = totalCostWithTax;
    }
}
