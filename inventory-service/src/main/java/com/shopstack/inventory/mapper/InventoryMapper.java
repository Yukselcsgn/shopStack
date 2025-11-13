package com.shopstack.inventory.mapper;

import com.shopstack.inventory.domain.InventoryItem;
import com.shopstack.inventory.dto.InventoryItemRequest;
import com.shopstack.inventory.dto.InventoryItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "availableQuantity", source = "initialQuantity")
    InventoryItem toEntity(InventoryItemRequest request);

    InventoryItemResponse toResponse(InventoryItem item);
}