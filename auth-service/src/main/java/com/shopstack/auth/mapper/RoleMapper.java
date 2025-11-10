package com.shopstack.auth.mapper;

import com.shopstack.auth.domain.Role;
import com.shopstack.auth.dto.RoleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);
}