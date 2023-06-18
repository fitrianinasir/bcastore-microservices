package com.shop.notification.service;

import com.shop.notification.dto.ResponseMessage;
import com.shop.notification.model.NotificationModel;
import com.shop.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public List<NotificationModel> getAllNotifications(){
        return notificationRepository.findAll();
    }

    public NotificationModel pushNotification(NotificationModel notificationModel){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(notificationModel.getRecipient());
            mailMessage.setText(notificationModel.getBody());
            mailMessage.setSubject(notificationModel.getSubject());
            javaMailSender.send(mailMessage);
            NotificationModel save = notificationRepository.save(notificationModel);
            System.out.println("Email sent successfully");
            return save;
        }catch(Exception e){
            return null;
        }

    }
}
