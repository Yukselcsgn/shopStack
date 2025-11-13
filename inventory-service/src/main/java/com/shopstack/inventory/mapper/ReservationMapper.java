package com.shopstack.inventory.mapper;

import com.shopstack.inventory.domain.StockReservation;
import com.shopstack.inventory.dto.ReserveStockResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReserveStockResponse toResponse(StockReservation reservation);
}