package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ReturnDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.PurchaseOrder;
import com.tj.inventorySpringBoot.entity.Return;
import com.tj.inventorySpringBoot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReturnService {

    @Autowired
    private ReturnRepository returnRepository;
@Autowired
private SupplierRepository supplierRepository;
    @Autowired
    private OrderRepository orderRepository;
@Autowired
private CustomerRepository customerRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    // Create a new return
    public ReturnDTO createReturn(ReturnDTO returnDTO) {
        Return returnEntity = convertToEntity(returnDTO);
        Return savedReturn = returnRepository.save(returnEntity);
        return convertToDTO(savedReturn);
    }

    // Update an existing return
    public ReturnDTO updateReturn(Long id, ReturnDTO returnDTO) {
        Optional<Return> existingReturnOptional = returnRepository.findById(id);
        if (existingReturnOptional.isPresent()) {
            Return existingReturn = existingReturnOptional.get();

            // Update fields
            existingReturn.setReason(returnDTO.getReasonForReturn());
            existingReturn.setQuantity(returnDTO.getQuantity());
            existingReturn.setReturnDate(returnDTO.getReturnDate());
            existingReturn.setReturnStatus(returnDTO.getReturnStatus());
            existingReturn.setAmountRefunded(returnDTO.getAmountRefunded());
            existingReturn.setReturnType(returnDTO.getReturnType());
            existingReturn.setRefundMethod(returnDTO.getRefundMethod());

            // Update associated customer order
            if (returnDTO.getCustomerOrderId() != null) {
                Order customerOrder = orderRepository.findById(returnDTO.getCustomerOrderId()).orElse(null);
                existingReturn.setCustomerOrder(customerOrder);
            } else {
                existingReturn.setCustomerOrder(null);
            }

            // Update associated supplier return
            if (returnDTO.getSupplierReturnId() != null) {
                PurchaseOrder supplierReturn = purchaseOrderRepository.findById(returnDTO.getSupplierReturnId()).orElse(null);
                existingReturn.setSupplierReturn(supplierReturn);
            } else {
                existingReturn.setSupplierReturn(null);
            }

            Return updatedReturn = returnRepository.save(existingReturn);
            return convertToDTO(updatedReturn);
        }
        return null; // Return null or throw an exception if not found
    }

    // Get all returns
    public List<ReturnDTO> getAllReturns() {
        List<Return> returns = returnRepository.findAll();
        return returns.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a return by ID
    public ReturnDTO getReturnById(Long id) {
        Optional<Return> returnOptional = returnRepository.findById(id);
        return returnOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a return by ID
    public void deleteReturn(Long id) {
        returnRepository.deleteById(id);
    }

    // Convert ReturnDTO to Return entity
    private Return convertToEntity(ReturnDTO returnDTO) {
        Return returnEntity = new Return();
        returnEntity.setReturnId(returnDTO.getReturnId());
        returnEntity.setReason(returnDTO.getReasonForReturn());
        returnEntity.setQuantity(returnDTO.getQuantity());
        returnEntity.setReturnDate(returnDTO.getReturnDate());
        returnEntity.setReturnStatus(returnDTO.getReturnStatus());
        returnEntity.setAmountRefunded(returnDTO.getAmountRefunded());
        returnEntity.setReturnType(returnDTO.getReturnType());
        returnEntity.setRefundMethod(returnDTO.getRefundMethod());

        // Set the associated customer order if it exists
        if (returnDTO.getCustomerOrderId() != null) {
            Order customerOrder = orderRepository.findById(returnDTO.getCustomerOrderId()).orElse(null);
            returnEntity.setCustomerOrder(customerOrder);
        }

        // Set the associated supplier return if it exists
        if (returnDTO.getSupplierReturnId() != null) {
            PurchaseOrder supplierReturn = purchaseOrderRepository.findById(returnDTO.getSupplierReturnId()).orElse(null);
            returnEntity.setSupplierReturn(supplierReturn);
        }

        return returnEntity;
    }

    // Convert Return entity to ReturnDTO
    private ReturnDTO convertToDTO(Return returnEntity) {
        ReturnDTO returnDTO = new ReturnDTO();
        returnDTO.setReturnId((returnEntity.getReturnId()));
        returnDTO.setReasonForReturn(returnEntity.getReason());
        returnDTO.setQuantity(returnEntity.getQuantity());
        returnDTO.setReturnDate(returnEntity.getReturnDate());
        returnDTO.setReturnStatus(returnEntity.getReturnStatus());
        returnDTO.setAmountRefunded(returnEntity.getAmountRefunded());
        returnDTO.setReturnType(returnEntity.getReturnType());
        returnDTO.setRefundMethod(returnEntity.getRefundMethod());

        // Set the customer order ID if it exists
        if (returnEntity.getCustomerOrder() != null) {
            returnDTO.setCustomerOrderId(returnEntity.getCustomerOrder().getOrderId());
        }

        // Set the supplier return ID if it exists
        if (returnEntity.getSupplierReturn() != null) {
            returnDTO.setSupplierReturnId(returnEntity.getSupplierReturn().getPurchaseOrderId());
        }

        return returnDTO;
    }
}
