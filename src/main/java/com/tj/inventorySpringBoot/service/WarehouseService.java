package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.WarehouseDTO;
import com.tj.inventorySpringBoot.entity.Warehouse;
import com.tj.inventorySpringBoot.repository.EmployeeRepository;
import com.tj.inventorySpringBoot.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    // Create a new warehouse
    public WarehouseDTO createWarehouse(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = convertToEntity(warehouseDTO);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return convertToDTO(savedWarehouse);
    }

    // Update an existing warehouse
    public WarehouseDTO updateWarehouse(Long id, WarehouseDTO warehouseDTO) {
        Optional<Warehouse> existingWarehouseOptional = warehouseRepository.findById(id);
        if (existingWarehouseOptional.isPresent()) {
            Warehouse existingWarehouse = existingWarehouseOptional.get();

            // Update fields with the new DTO fields
            existingWarehouse.setWarehouseName(warehouseDTO.getWarehouseName());
            existingWarehouse.setWarehouseAddress(warehouseDTO.getWarehouseAddress());
            existingWarehouse.setCapacity(warehouseDTO.getCapacity());
            existingWarehouse.setContactInfo(warehouseDTO.getContactInfo());
            existingWarehouse.setStatus(warehouseDTO.getStatus());
            existingWarehouse.setManager(employeeRepository.findById(warehouseDTO.getManagerId()).orElse(null));

            Warehouse updatedWarehouse = warehouseRepository.save(existingWarehouse);
            return convertToDTO(updatedWarehouse);
        }
        return null; // Handle not found case as needed
    }

    // Get all warehouses
    public List<WarehouseDTO> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a warehouse by ID
    public WarehouseDTO getWarehouseById(Long id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        return warehouseOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a warehouse by ID
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    // Convert WarehouseDTO to Warehouse entity
    private Warehouse convertToEntity(WarehouseDTO warehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(warehouseDTO.getWarehouseId());
        warehouse.setWarehouseName(warehouseDTO.getWarehouseName());
        warehouse.setWarehouseAddress(warehouseDTO.getWarehouseAddress());
        warehouse.setCapacity(warehouseDTO.getCapacity());
        warehouse.setContactInfo(warehouseDTO.getContactInfo());
        warehouse.setStatus(warehouseDTO.getStatus());
        warehouse.setManager(employeeRepository.findById(warehouseDTO.getManagerId()).get());
        return warehouse;
    }

    // Convert Warehouse entity to WarehouseDTO
    private WarehouseDTO convertToDTO(Warehouse warehouse) {
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setWarehouseId(warehouse.getWarehouseId());
        warehouseDTO.setWarehouseName(warehouse.getWarehouseName());
        warehouseDTO.setWarehouseAddress(warehouse.getWarehouseAddress());
        warehouseDTO.setCapacity(warehouse.getCapacity());
        warehouseDTO.setContactInfo(warehouse.getContactInfo());
        warehouseDTO.setStatus(warehouse.getStatus());
        warehouseDTO.setManagerId(warehouse.getManager() != null ? warehouse.getManager().getEmployeeId() : null);
        return warehouseDTO;
    }
}
