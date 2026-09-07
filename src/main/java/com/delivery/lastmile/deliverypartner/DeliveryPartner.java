package com.delivery.lastmile.deliverypartner;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="delivery_partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String name;
     private String phoneNumber;
     private Double currentLatitude;
    private Double currentLongitude;

    @Enumerated(EnumType.STRING)
    private DeliveryPartnerStatus status;


}
