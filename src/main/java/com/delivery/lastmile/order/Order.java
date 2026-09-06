package com.delivery.lastmile.order;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String pickupAddress;
    private String deliveryAddress;
    private Double deliveryLatitude;
    private Double deliverylongitude;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
