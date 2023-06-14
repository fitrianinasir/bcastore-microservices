package com.shop.notification.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_notification_hist")
public class NotificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private String order_id;

    @Column(name = "body")
    private String body;

    @Column(name = "send_date")
    private String send_date;

    @Column(name = "status")
    private String status;
}
