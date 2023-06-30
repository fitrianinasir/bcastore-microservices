package com.shop.notification.service;

import com.shop.notification.model.NotificationModel;
import com.shop.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public Optional<NotificationModel> getNotificationById(Integer id){return notificationRepository.findById(id);}

    public NotificationModel pushNotification(NotificationModel notificationModel){
        try{
            String emailBody = "Hi Dear!\n" +
                    "\n" +
                    "Your order with details below has been placed!\n" +
                    "product\t\tamount\tprice\t\ttotal\n" +
                    notificationModel.getBody() +
                    "\n" +
                    "\n" +
                    "Thank you for your order!\n" +
                    "\n" +
                    "Regards,\n" +
                    "BCA Shop";
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(notificationModel.getRecipient());
            mailMessage.setText(emailBody);
            mailMessage.setSubject("BCA STORE");

            javaMailSender.send(mailMessage);
            notificationModel.setSubject("BCA STORE");
            notificationModel.setStatus("SUCCESS");
            NotificationModel save = notificationRepository.save(notificationModel);
            System.out.println("Email sent successfully");
            return save;
        }catch(Exception e){
            return null;
        }
    }
}
