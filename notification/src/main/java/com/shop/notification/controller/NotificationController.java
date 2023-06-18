package com.shop.notification.controller;

import com.shop.notification.dto.ResponseMessage;
import com.shop.notification.model.NotificationModel;
import com.shop.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notifications")
    public @ResponseBody ResponseEntity<ResponseMessage> getAllNotifications(){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Data retrieved successfully");
        responseMessage.setData(notificationService.getAllNotifications());
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/push-notif")
    public @ResponseBody ResponseEntity<ResponseMessage> pushNotification(@RequestBody NotificationModel notificationModel){
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(200);
        responseMessage.setMessage("Notification sent successfully");
        responseMessage.setData(notificationService.pushNotification(notificationModel));
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
