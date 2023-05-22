package com.codenames.mapper;

import com.codenames.dto.UserAuthRoleDto;
import com.codenames.entity.UserAuthRoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAuthRoleMapper {

    UserAuthRoleDto userAuthRoleEntityToUserAuthRoleDto(UserAuthRoleEntity authRoleEntity);
}
