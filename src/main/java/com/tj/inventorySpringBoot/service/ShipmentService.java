package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.ShipmentDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.Shipment;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import com.tj.inventorySpringBoot.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Create a new shipment
    public ShipmentDTO createShipment(ShipmentDTO shipmentDTO) {
        Shipment shipment = convertToEntity(shipmentDTO);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return convertToDTO(savedShipment);
    }

    // Update an existing shipment
    public ShipmentDTO updateShipment(Long id, ShipmentDTO shipmentDTO) {
        Optional<Shipment> existingShipmentOptional = shipmentRepository.findById(id);
        if (existingShipmentOptional.isPresent()) {
            Shipment existingShipment = existingShipmentOptional.get();

            // Update shipment details
            existingShipment.setTrackingNumber(shipmentDTO.getTrackingNumber());
            
            existingShipment.setCarrierName(shipmentDTO.getCarrierName());
            existingShipment.setShippingDate(shipmentDTO.getShippingDate());
            existingShipment.setDeliveryDate(shipmentDTO.getDeliveryDate());
            existingShipment.setShipmentStatus(shipmentDTO.getShipmentStatus());
            existingShipment.setDeliveryAddress(shipmentDTO.getDeliveryAddress());
            existingShipment.setShippingCost(shipmentDTO.getShippingCost());

            // Update associated Order entity if provided
            if (shipmentDTO.getOrderId() != null) {
                Order order = orderRepository.findById(shipmentDTO.getOrderId()).orElse(null);
                existingShipment.setOrder(order);
            }

            Shipment updatedShipment = shipmentRepository.save(existingShipment);
            return convertToDTO(updatedShipment);
        }
        return null; // Handle appropriately if the shipment is not found
    }

    // Get all shipments
    public List<ShipmentDTO> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return shipments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a shipment by ID
    public ShipmentDTO getShipmentById(Long id) {
        Optional<Shipment> shipmentOptional = shipmentRepository.findById(id);
        return shipmentOptional.map(this::convertToDTO).orElse(null);
    }

    // Delete a shipment by ID
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }

    // Convert ShipmentDTO to Shipment entity
    private Shipment convertToEntity(ShipmentDTO shipmentDTO) {
        Shipment shipment = new Shipment();

        shipment.setShipmentId(shipmentDTO.getShipmentId());

        shipment.setTrackingNumber(shipmentDTO.getTrackingNumber());
        shipment.setCarrierName(shipmentDTO.getCarrierName());
        shipment.setShippingDate(shipmentDTO.getShippingDate());
        shipment.setDeliveryDate(shipmentDTO.getDeliveryDate());
        shipment.setShipmentStatus(shipmentDTO.getShipmentStatus());
        shipment.setDeliveryAddress(shipmentDTO.getDeliveryAddress());
        shipment.setShippingCost(shipmentDTO.getShippingCost());

        // Set associated Order entity
        if (shipmentDTO.getOrderId() != null) {
            Order order = orderRepository.findById(shipmentDTO.getOrderId()).orElse(null);
            shipment.setOrder(order);
        }

        return shipment;
    }

    // Convert Shipment entity to ShipmentDTO
    private ShipmentDTO convertToDTO(Shipment shipment) {
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setShipmentId(shipment.getShipmentId());
        shipmentDTO.setOrderId(shipment.getOrder().getOrderId());
        shipmentDTO.setTrackingNumber(shipment.getTrackingNumber());
        shipmentDTO.setCarrierName(shipment.getCarrierName());
        shipmentDTO.setShippingDate(shipment.getShippingDate());
        shipmentDTO.setDeliveryDate(shipment.getDeliveryDate());
        shipmentDTO.setShipmentStatus(shipment.getShipmentStatus());
        shipmentDTO.setDeliveryAddress(shipment.getDeliveryAddress());
        shipmentDTO.setShippingCost(shipment.getShippingCost());

        // Set associated Order ID if it exists
        if (shipment.getOrder() != null) {
            shipmentDTO.setOrderId(shipment.getOrder().getOrderId());
        }

        return shipmentDTO;
    }
}
