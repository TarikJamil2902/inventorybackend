package com.tj.inventorySpringBoot.service;

import com.tj.inventorySpringBoot.dto.NotificationDTO;
import com.tj.inventorySpringBoot.entity.Notification;
import com.tj.inventorySpringBoot.entity.User;
import com.tj.inventorySpringBoot.enums.NotificationType;
import com.tj.inventorySpringBoot.repository.NotificationRepository;
import com.tj.inventorySpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new notification
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        notification.setCreatedAt(LocalDateTime.now());
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }

    // Update an existing notification
    public NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setMessage(notificationDTO.getMessage());
            notification.setStatus(notificationDTO.getStatus());
            notification.setUpdatedAt(LocalDateTime.now());



            if (notificationDTO.getUserName() != null) {
                User user = userRepository.findByUserName(notificationDTO.getUserName()).orElse(null);
                notification.setUser(user);
            }

            Notification updatedNotification = notificationRepository.save(notification);
            return convertToDTO(updatedNotification);
        }
        return null;
    }

    // Get all notifications
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a notification by its ID
    public NotificationDTO getNotificationById(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        return notificationOptional.map(this::convertToDTO).orElse(null);
    }

    // Mark a notification as read
    public NotificationDTO markAsRead(Long id) {
        Optional<Notification> notificationOptional = notificationRepository.findById(id);
        if (notificationOptional.isPresent()) {
            Notification notification = notificationOptional.get();
            notification.setStatus("READ");
            notification.setUpdatedAt(LocalDateTime.now());
            notificationRepository.save(notification);
            return convertToDTO(notification);
        }
        return null;
    }

    // Delete a notification by its ID
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    // Convert NotificationDTO to Notification entity
    private Notification convertToEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setNotificationId(notificationDTO.getNotificationId());
        notification.setMessage(notificationDTO.getMessage());
        notification.setStatus(notificationDTO.getStatus());



        if (notificationDTO.getUserName() != null) {
            User user = userRepository.findById(notificationDTO.getUserName()).orElse(null);
            notification.setUser(user);
        }

        return notification;
    }

    // Convert Notification entity to NotificationDTO
    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setStatus(notification.getStatus());
        notificationDTO.setCreatedAt(notification.getCreatedAt());

        

        if (notification.getUser() != null) {
            notificationDTO.setUserName(notification.getUser().getUserName());
        }

        return notificationDTO;
    }
}