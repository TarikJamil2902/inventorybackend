package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.OrderDTO;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.OrderItem;
import com.tj.inventorySpringBoot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
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

    // Create a new order
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    // Update an existing order
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Optional<Order> existingOrderOptional = orderRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            updateOrderFromDTO(existingOrder, orderDTO);
            Order updatedOrder = orderRepository.save(existingOrder);
            return convertToDTO(updatedOrder);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    // Get all orders
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get an order by its ID
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // Delete an order by its ID
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new RuntimeException("Order not found with id: " + id);
        }
    }

    // Convert OrderDTO to Order entity
    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        updateOrderFromDTO(order, orderDTO);
        return order;
    }

    // Update Order entity from OrderDTO
    private void updateOrderFromDTO(Order order, OrderDTO orderDTO) {
        order.setOrderId(orderDTO.getOrderId());

        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerContact(orderDTO.getCustomerContact());
        order.setOrderItemIds(orderDTO.getOrderItemIds());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setStatus(orderDTO.getStatus());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setShipmentId(orderDTO.getShipmentId());
        order.setEmployeeId(orderDTO.getEmployeeId());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setBillingAddress(orderDTO.getBillingAddress());
        order.setShippingMethod(orderDTO.getShippingMethod());
        order.setPaymentStatus(orderDTO.getPaymentStatus());



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
        orderDTO.setCustomerContact(order.getCustomerContact());
        orderDTO.setOrderItemIds(order.getOrderItemIds());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setShipmentId(order.getShipmentId());
        orderDTO.setEmployeeId(order.getEmployeeId());

        orderDTO.setStatus(order.getStatus());


        return orderDTO;
    }
}