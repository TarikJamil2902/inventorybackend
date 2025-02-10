package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.OrderDTO;
import com.tj.inventorySpringBoot.entity.Customer;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.OrderItem;
import com.tj.inventorySpringBoot.enums.OrderStatus;
import com.tj.inventorySpringBoot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private TaxRepository taxRepository;


    // Method to create a new order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    // Method to update an existing order
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            existingOrder.setCustomerName(orderDTO.getCustomerName());
            existingOrder.setCustomerContact(orderDTO.getCustomerContact());
            existingOrder.setTotalAmount(orderDTO.getTotalAmount());
            existingOrder.setShippingAddress(orderDTO.getShippingAddress());
            existingOrder.setBillingAddress(orderDTO.getBillingAddress());
            existingOrder.setPaymentStatus(orderDTO.getPaymentStatus());
            existingOrder.setShippingMethod(orderDTO.getShippingMethod());
            existingOrder.setStatus(orderDTO.getStatus());

            // Update the order items if provided
//            if (orderDTO.getOrderItemIds() != null) {
//                List<OrderItem> orderItems = new ArrayList<>();
//
//                for (Long orderItemId : orderDTO.getOrderItemIds()) {
//                    orderItemRepository.findById(orderItemId).ifPresent(orderItems::add);
//                }
//
//                existingOrder.setOrderItems((Set<OrderItem>) orderItems);
//            }

            // Update customer if provided
            if (orderDTO.getCustomerId() != null) {
                customerRepository.findById(orderDTO.getCustomerId()).ifPresent(existingOrder::setCustomer);
            }

            // Update employee if provided
            if (orderDTO.getEmployeeId() != null) {
                employeeRepository.findById(orderDTO.getEmployeeId()).ifPresent(existingOrder::setEmployee);
            }

            // Update shipment if provided
            if (orderDTO.getShipmentId() != null) {
                shipmentRepository.findById(orderDTO.getShipmentId()).ifPresent(existingOrder::setShipment);
            }

            orderRepository.save(existingOrder);
            return convertToDTO(existingOrder);
        }
        return null;  // Handle as needed (e.g., return 404 or throw an exception)
    }

    // Method to get all orders
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Method to get an order by its ID
    public OrderDTO getOrderById(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return convertToDTO(orderOptional.get());
        }
        return null;
    }

    // Method to delete an order by its ID
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Convert OrderDTO to Order entity
    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerContact(orderDTO.getCustomerContact());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus(orderDTO.getStatus());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setBillingAddress(orderDTO.getBillingAddress());
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setShippingMethod(orderDTO.getShippingMethod());



        if (order.getOrderItems() != null) {
            List<Long> orderItemIds = order.getOrderItems().stream()
                    .map(OrderItem::getOrderItemId)
                    .collect(Collectors.toList());
            orderDTO.setOrderItemIds(orderItemIds);
        }

        // Set customer
        if (orderDTO.getCustomerId() != null) {
            customerRepository.findById(orderDTO.getCustomerId()).ifPresent(order::setCustomer);
        }

        // Set employee
        if (orderDTO.getEmployeeId() != null) {
            employeeRepository.findById(orderDTO.getEmployeeId()).ifPresent(order::setEmployee);
        }

        // Set shipment
        if (orderDTO.getShipmentId() != null) {
            shipmentRepository.findById(orderDTO.getShipmentId()).ifPresent(order::setShipment);
        }
        return order;
    }

    // Convert Order entity to OrderDTO
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setCustomerContact(order.getCustomerContact());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setBillingAddress(order.getBillingAddress());
        orderDTO.setShippingAddress(order.getShippingAddress());
        orderDTO.setShippingMethod(order.getShippingMethod());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setStatus(order.getStatus());

        if (order.getOrderItems() != null) {
            List<Long> orderItemIds = order.getOrderItems().stream()
                    .map(OrderItem::getOrderItemId)
                    .collect(Collectors.toList());
            orderDTO.setOrderItemIds(orderItemIds);
        }

        // Set customer ID
        if (order.getCustomer() != null) {
            orderDTO.setCustomerId(order.getCustomer().getCustomerId());
        }

        // Set employee ID
        if (order.getEmployee() != null) {
            orderDTO.setEmployeeId(order.getEmployee().getEmployeeId());
        }

        // Set shipment ID
        if (order.getShipment() != null) {
            orderDTO.setShipmentId(order.getShipment().getShipmentId());
        }

        return orderDTO;
    }
}
