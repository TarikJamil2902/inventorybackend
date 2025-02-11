package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.PurchaseOrderDTO;
import com.tj.inventorySpringBoot.entity.*;
import com.tj.inventorySpringBoot.enums.PurchaseOrderStatus;
import com.tj.inventorySpringBoot.repository.PurchaseOrderItemRepository;
import com.tj.inventorySpringBoot.repository.PurchaseOrderRepository;
import com.tj.inventorySpringBoot.repository.SupplierRepository;
import com.tj.inventorySpringBoot.repository.EmployeeRepository;  // Added Employee repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;



    // Create a new purchase order
    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = convertToEntity(purchaseOrderDTO);
        PurchaseOrder savedpurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return convertToDTO(savedpurchaseOrder);
    }

    // Update an existing purchase order
    public PurchaseOrderDTO updatePurchaseOrder(Long id, PurchaseOrderDTO purchaseOrderDTO) {
        Optional<PurchaseOrder> existingPurchaseOrderOptional = purchaseOrderRepository.findById(id);
        if (existingPurchaseOrderOptional.isPresent()) {
            PurchaseOrder existingPurchaseOrder = existingPurchaseOrderOptional.get();

            // Update fields
            existingPurchaseOrder.setTotalAmount(purchaseOrderDTO.getTotalAmount());
            existingPurchaseOrder.setPurchaseOrderItems(purchaseOrderDTO.getPurchaseOrderItems());
            existingPurchaseOrder.setSupplierId(purchaseOrderDTO.getSupplierId());
            existingPurchaseOrder.setCreatedBy(purchaseOrderDTO.getCreatedBy());
            existingPurchaseOrder.setOrderDate(purchaseOrderDTO.getOrderDate());
            existingPurchaseOrder.setDeliveryDate(purchaseOrderDTO.getDeliveryDate());
            existingPurchaseOrder.setPaymentTerms(purchaseOrderDTO.getPaymentTerms());
            existingPurchaseOrder.setStatus(purchaseOrderDTO.getStatus());
            existingPurchaseOrder.setPurchaseOrderId(purchaseOrderDTO.getPurchaseOrderId());






            PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.save(existingPurchaseOrder);
            return convertToDTO(updatedPurchaseOrder);
        }
        return null; // Return null or throw an exception if not found
    }

    // Get all purchase orders
    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a purchase order by its ID
    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        Optional<PurchaseOrder> purchaseOrderOptional = purchaseOrderRepository.findById(id);
        if (purchaseOrderOptional.isPresent()) {
            return convertToDTO(purchaseOrderOptional.get());
        }
        return null; // Return null or throw an exception if not found
    }

    // Delete a purchase order by its ID
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    // Convert PurchaseOrderDTO to PurchaseOrder entity
    private PurchaseOrder convertToEntity(PurchaseOrderDTO purchaseOrderDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseOrderId(purchaseOrderDTO.getPurchaseOrderId());

        purchaseOrder.setPurchaseOrderItems(purchaseOrderDTO.getPurchaseOrderItems());
        purchaseOrder.setSupplierId(purchaseOrderDTO.getSupplierId());
        purchaseOrder.setOrderDate(purchaseOrderDTO.getOrderDate());
        purchaseOrder.setDeliveryDate(purchaseOrderDTO.getDeliveryDate());
        purchaseOrder.setTotalAmount(purchaseOrderDTO.getTotalAmount());
        purchaseOrder.setStatus(purchaseOrderDTO.getStatus());
        purchaseOrder.setPaymentTerms(purchaseOrderDTO.getPaymentTerms());

        purchaseOrder.setCreatedBy(purchaseOrderDTO.getCreatedBy());





        return purchaseOrder;
    }

    // Convert PurchaseOrder entity to PurchaseOrderDTO
    private PurchaseOrderDTO convertToDTO(PurchaseOrder purchaseOrder) {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        purchaseOrderDTO.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        purchaseOrderDTO.setTotalAmount(purchaseOrder.getTotalAmount());
        purchaseOrderDTO.setPurchaseOrderItems(purchaseOrder.getPurchaseOrderItems());
        purchaseOrderDTO.setSupplierId(purchaseOrder.getSupplierId());
        purchaseOrderDTO.setCreatedBy(purchaseOrder.getCreatedBy());
        purchaseOrderDTO.setOrderDate(purchaseOrder.getOrderDate());
        purchaseOrderDTO.setDeliveryDate(purchaseOrder.getDeliveryDate());
        purchaseOrderDTO.setStatus(purchaseOrder.getStatus());
        purchaseOrderDTO.setPaymentTerms(purchaseOrder.getPaymentTerms());

        return purchaseOrderDTO;
    }
}
