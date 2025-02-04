package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.InventoryDTO;
import com.tj.inventorySpringBoot.entity.Inventory;
import com.tj.inventorySpringBoot.entity.Product;
import com.tj.inventorySpringBoot.entity.Warehouse;
import com.tj.inventorySpringBoot.repository.InventoryRepository;
import com.tj.inventorySpringBoot.repository.ProductRepository;
import com.tj.inventorySpringBoot.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    // Create a new inventory record
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = convertToEntity(inventoryDTO);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return convertToDTO(savedInventory);
    }

    // Update an existing inventory record
    public InventoryDTO updateInventory(Long id, InventoryDTO inventoryDTO) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        if (inventoryOptional.isPresent()) {
            Inventory inventory = inventoryOptional.get();
            inventory.setQuantityOnHand(inventoryDTO.getQuantityOnHand());
            inventory.setQuantityAllocated(inventoryDTO.getQuantityAllocated());

            if (inventoryDTO.getProductId() != null) {
                Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);
                inventory.setProduct(product);
            }

            if (inventoryDTO.getWarehouseId() != null) {
                Warehouse warehouse = warehouseRepository.findById(inventoryDTO.getWarehouseId()).orElse(null);
                inventory.setWarehouse(warehouse);
            }

            Inventory updatedInventory = inventoryRepository.save(inventory);
            return convertToDTO(updatedInventory);
        }
        return null;
    }

    // Get all inventory records
    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get an inventory record by ID
    public InventoryDTO getInventoryById(Long id) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);
        return inventoryOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete an inventory record by ID
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    // Convert InventoryDTO to Inventory entity
    private Inventory convertToEntity(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryDTO.getInventoryId());
        inventory.setQuantityOnHand(inventoryDTO.getQuantityOnHand());
        inventory.setQuantityAllocated(inventoryDTO.getQuantityAllocated());
        inventory.setQuantityAvailable(inventoryDTO.getQuantityAvailable());

        if (inventoryDTO.getProductId() != null) {
            Product product = productRepository.findById(inventoryDTO.getProductId()).orElse(null);
            inventory.setProduct(product);
        }

        if (inventoryDTO.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepository.findById(inventoryDTO.getWarehouseId()).orElse(null);
            inventory.setWarehouse(warehouse);
        }

        return inventory;
    }

    // Convert Inventory entity to InventoryDTO
    private InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setInventoryId(inventory.getInventoryId());
        inventoryDTO.setQuantityOnHand(inventory.getQuantityOnHand());
        inventoryDTO.setQuantityAllocated(inventory.getQuantityAllocated());
        inventoryDTO.setQuantityAvailable(inventory.getQuantityAvailable());

        if (inventory.getProduct() != null) {
            inventoryDTO.setProductId(inventory.getProduct().getProductId());
        }

        if (inventory.getWarehouse() != null) {
            inventoryDTO.setWarehouseId(inventory.getWarehouse().getWarehouseId());
        }

        return inventoryDTO;
    }
}
