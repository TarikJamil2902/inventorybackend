package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.PaymentDTO;
import com.tj.inventorySpringBoot.entity.Customer;
import com.tj.inventorySpringBoot.entity.Order;
import com.tj.inventorySpringBoot.entity.Payment;
import com.tj.inventorySpringBoot.repository.CustomerRepository;
import com.tj.inventorySpringBoot.repository.OrderRepository;
import com.tj.inventorySpringBoot.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Method to create a new payment
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    // Method to update an existing payment
    public PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setAmount(paymentDTO.getAmount());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setPaymentStatus(paymentDTO.getPaymentStatus());
            payment.setCurrency(paymentDTO.getCurrency());
            payment.setCustomerId(paymentDTO.getCustomerId());
            payment.setOrderId(paymentDTO.getOrderId());
            payment.setTransactionId(paymentDTO.getTransactionId());
            payment.setPaymentDate(paymentDTO.getPaymentDate());
            payment.setPaymentId(paymentDTO.getPaymentId());



            Payment updatedPayment = paymentRepository.save(payment);
            return convertToDTO(updatedPayment);
        }
        return null; // Return null if payment is not found (you can handle it in the controller)
    }

    // Get all payments
    public List<PaymentDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a payment by its ID
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            return convertToDTO(paymentOptional.get());
        }
        return null; // Return null or handle with an exception
    }

    // Delete a payment by its ID
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    // Convert PaymentDTO to Payment entity
    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setPaymentStatus(paymentDTO.getPaymentStatus());
        payment.setCurrency(paymentDTO.getCurrency());
        payment.setPaymentDate(paymentDTO.getPaymentDate());
        payment.setOrderId(paymentDTO.getOrderId());
        payment.setTransactionId(paymentDTO.getTransactionId());
        payment.setCustomerId(paymentDTO.getCustomerId());


        // Fetch Order and Customer by their IDs
        // For now, we're not setting these fields directly in this method,
        // assuming that the paymentDTO will contain valid orderId and customerId
        return payment;
    }

    // Convert Payment entity to PaymentDTO
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentMethod(payment.getPaymentMethod());
        paymentDTO.setPaymentStatus(payment.getPaymentStatus());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setPaymentDate(payment.getPaymentDate());
        paymentDTO.setCustomerId(payment.getCustomerId());
        paymentDTO.setTransactionId(payment.getTransactionId());
        paymentDTO.setOrderId(payment.getOrderId());

        paymentDTO.setTransactionId(payment.getTransactionId());

        // Optionally set orderId and customerId if needed


        return paymentDTO;
    }
}
