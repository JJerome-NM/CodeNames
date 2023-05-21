package com.codenames.controllers;

import com.codenames.dto.CredentialDto;
import com.codenames.dto.SignUpDto;
import com.codenames.dto.UserAuthDto;
import com.codenames.mapper.UserMapper;
import com.codenames.provider.UserAuthProvider;
import com.codenames.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/check_user_is_auth")
    public boolean checkUserIsAuth(){
        return true;
    }
}
