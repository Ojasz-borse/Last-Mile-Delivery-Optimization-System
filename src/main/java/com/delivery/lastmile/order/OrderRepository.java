package com.delivery.lastmile.order;
import com.delivery.lastmile.deliverypartner.DeliveryPartner;
import com.delivery.lastmile.deliverypartner.DeliveryPartnerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long>{
    List<DeliveryPartner> findByStatus(
            DeliveryPartnerStatus status
    );
}
