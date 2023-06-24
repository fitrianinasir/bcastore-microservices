package com.shop.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_order_mst")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "id_customer")
    private Integer id_customer;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "id_product")
    private Integer id_product;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_price")
    private Integer product_price;

    @Column(name = "payment_name")
    private String payment_name;

    @Column(name = "payment_status")
    private String payment_status;

    @Column(name = "notification_status")
    private String notification_status;


    @Column(name = "num_of_orders")
    private Integer num_of_orders;

    @Column(name = "total_charging")
    private Integer total_charging;

    @Column(name = "order_date")
    private String order_date;

}
