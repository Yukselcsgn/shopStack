package com.shopstack.inventory.repository;

import com.shopstack.inventory.domain.StockReservation;
import com.shopstack.inventory.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface StockReservationRepository extends JpaRepository<StockReservation, UUID> {
    List<StockReservation> findByOrderId(UUID orderId);
    List<StockReservation> findByStatusAndExpiresAtBefore(ReservationStatus status, LocalDateTime before);
}