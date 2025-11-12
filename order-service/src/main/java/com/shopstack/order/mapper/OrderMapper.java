package com.shopstack.order.mapper;

import com.shopstack.order.domain.Order;
import com.shopstack.order.dto.OrderRequest;
import com.shopstack.order.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    Order toEntity(OrderRequest request);

    OrderResponse toResponse(Order order);
}