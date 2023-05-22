package com.codenames.controllers;

import com.codenames.dto.CredentialDto;
import com.codenames.dto.SignUpDto;
import com.codenames.dto.UserAuthDto;
import com.codenames.dto.UserAuthRoleDto;
import com.codenames.entity.UserAuthRoleEntity;
import com.codenames.enums.DefaultUserAuthRole;
import com.codenames.mapper.UserAuthRoleMapper;
import com.codenames.mapper.UserMapper;
import com.codenames.provider.UserAuthProvider;
import com.codenames.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    private final UserAuthProvider userAuthProvider;

    private final UserMapper userMapper;

    private final UserAuthRoleMapper userAuthRoleMapper;

    @PostMapping("/login")
    public ResponseEntity<UserAuthDto> loginUser(@Valid @RequestBody CredentialDto credentialDto){
        UserAuthDto user = userMapper.userEntityToUserAuthDto(userService.login(credentialDto));

        user.setJwtToken(userAuthProvider.createToken(user.getLogin()));

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthDto> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        UserAuthDto user = userMapper.userEntityToUserAuthDto(userService.register(signUpDto));

        user.setJwtToken(userAuthProvider.createToken(user.getLogin()));

        return ResponseEntity.ok(user);
    }

    @GetMapping("/whoami")
    public ResponseEntity<UserAuthRoleDto> whoami(Authentication authentication){

        // TODO: 22.05.2023 Perhaps this method will need to be corrected, but this is after adding a full-fledged user role assignment system

        UserAuthRoleEntity userAuthRole = userService.getUserAuthRole(authentication)
                .orElse(new UserAuthRoleEntity(-1L, null, DefaultUserAuthRole.GUEST.getRole()));

        return ResponseEntity.ok(userAuthRoleMapper.userAuthRoleEntityToUserAuthRoleDto(userAuthRole));
    }
}
