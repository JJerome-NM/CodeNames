package com.codenames.mapper;

import com.codenames.dto.SignUpDto;
import com.codenames.dto.UserAuthDto;
import com.codenames.dto.UserDto;
import com.codenames.entity.UserEntity;
import com.codenames.domain.game.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userEntityToUserDto(UserEntity userEntity);

    @Mapping(target = "login", source = "email")
    UserEntity signUpToUserEntity(SignUpDto userEntity);

    UserAuthDto userEntityToUserAuthDto(UserEntity userEntity);

    User userEntityToUser(UserEntity userEntity);
}
