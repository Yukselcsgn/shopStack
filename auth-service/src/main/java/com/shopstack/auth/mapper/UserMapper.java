package com.shopstack.auth.mapper;

import com.shopstack.auth.domain.User;
import com.shopstack.auth.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    @Mapping(source = "roles", target = "roles")
    UserDto toDto(User user);
}
