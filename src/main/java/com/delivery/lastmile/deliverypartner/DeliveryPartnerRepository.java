package com.delivery.lastmile.deliverypartner;

import aQute.bnd.annotation.metatype.Meta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Long> {

   List<DeliveryPartner> findByStatus(
           DeliveryPartnerStatus status
   );
}
