package com.shop.payment.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_payment_hist")
public class PushPaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private Integer order_id;

    @Column(name = "payment_type")
    private String payment_type;

    @Column(name = "total_payment")
    private Integer total_payment;

    @Column(name = "payment_date")
    private String payment_date;

    @Column(name = "status_payment")
    private String status_payment;
}
