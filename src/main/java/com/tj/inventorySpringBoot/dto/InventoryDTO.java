package com.tj.inventorySpringBoot.dto;
public class InventoryDTO {

    private Long inventoryId;
    private Long productId;    // Reference to the Product ID
    private Long warehouseId;  // Reference to the Warehouse ID
    private Integer quantityOnHand;
    private Integer quantityAllocated;
    private Integer quantityAvailable;

    // Getters and Setters
    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public Integer getQuantityAllocated() {
        return quantityAllocated;
    }

    public void setQuantityAllocated(Integer quantityAllocated) {
        this.quantityAllocated = quantityAllocated;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }
}
