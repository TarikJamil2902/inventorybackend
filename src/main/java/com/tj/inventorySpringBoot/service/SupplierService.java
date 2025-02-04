package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.SupplierDTO;
import com.tj.inventorySpringBoot.entity.Supplier;
import com.tj.inventorySpringBoot.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // Create a new supplier
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = convertToEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToDTO(savedSupplier);
    }

    // Update an existing supplier
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        Optional<Supplier> existingSupplierOptional = supplierRepository.findById(id);
        if (existingSupplierOptional.isPresent()) {
            Supplier existingSupplier = existingSupplierOptional.get();

            // Update the supplier's fields
            existingSupplier.setName(supplierDTO.getName());
            existingSupplier.setEmail(supplierDTO.getEmail()); // Use updated field
            existingSupplier.setPhone(supplierDTO.getPhone());
            existingSupplier.setAddress(supplierDTO.getAddress());
            existingSupplier.setPaymentTerms(supplierDTO.getPaymentTerms()); // New field
            existingSupplier.setSupplierRating(supplierDTO.getSupplierRating()); // New field
            existingSupplier.setStatus(supplierDTO.getStatus()); // New field

            Supplier updatedSupplier = supplierRepository.save(existingSupplier);
            return convertToDTO(updatedSupplier);
        }
        return null; // Handle appropriately if the supplier is not found
    }

    // Get all suppliers
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a supplier by ID
    public SupplierDTO getSupplierById(Long id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        return supplierOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a supplier by ID
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    // Convert SupplierDTO to Supplier entity
    private Supplier convertToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(supplierDTO.getSupplierId()); // Updated field
        supplier.setName(supplierDTO.getName());
        supplier.setEmail(supplierDTO.getEmail()); // Updated field
        supplierDTO.setPhone(supplier.getPhone());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setPaymentTerms(supplierDTO.getPaymentTerms()); // New field
        supplier.setSupplierRating(supplierDTO.getSupplierRating()); // New field
        supplier.setStatus(supplierDTO.getStatus()); // New field
        return supplier;
    }

    // Convert Supplier entity to SupplierDTO
    private SupplierDTO convertToDTO(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getSupplierId()); // Updated field
        supplierDTO.setName(supplier.getName());
        supplierDTO.setEmail(supplier.getEmail()); // Updated field
        supplierDTO.setPhone(supplier.getPhone());
        supplierDTO.setAddress(supplier.getAddress());
        supplierDTO.setPaymentTerms(supplier.getPaymentTerms()); // New field
        supplierDTO.setSupplierRating(supplier.getSupplierRating()); // New field
        supplierDTO.setStatus(supplier.getStatus()); // New field
        return supplierDTO;
    }
}
