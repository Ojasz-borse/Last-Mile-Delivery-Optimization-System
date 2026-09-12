package com.delivery.lastmile.tracking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationUpdateRepository
        extends JpaRepository<LocationUpdate, Long> {

    List<LocationUpdate> findByDeliveryPartnerIdOrderByTimestampDesc(
            Long deliveryPartnerId
    );
}