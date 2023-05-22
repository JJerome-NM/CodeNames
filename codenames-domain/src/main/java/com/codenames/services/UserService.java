package com.codenames.services;

import com.codenames.dto.CredentialDto;
import com.codenames.dto.SignUpDto;
import com.codenames.entity.UserEntity;
import com.codenames.entity.UserAuthRoleEntity;
import com.codenames.exception.UserAlreadyExistsException;
import com.codenames.exception.UserNotFoundException;
import com.codenames.mapper.UserMapper;
import com.codenames.repository.UserAuthRoleRepository;
import com.codenames.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final UserAuthRoleRepository userAuthRoleRepository;

    public <T> T findByNickname(Class<T> rClass, String login){
        return userRepository.findByLogin(rClass, login)
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserEntity login(CredentialDto credentialDto){
        UserEntity userEntity = userRepository.findByLogin(UserEntity.class, credentialDto.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User not found on database", HttpStatus.NOT_FOUND));

        System.out.println(userEntity);

        if (passwordEncoder.matches(CharBuffer.wrap(credentialDto.getPassword()), userEntity.getPassword())){
            return userEntity;
        }

        throw new UserNotFoundException("Bad password", HttpStatus.BAD_REQUEST);
    }

    public UserEntity register(SignUpDto signUpDto){
        Optional<UserEntity> optionalUser = userRepository.findByLogin(UserEntity.class, signUpDto.getEmail());

        if (optionalUser.isPresent()){
            throw new UserAlreadyExistsException(
                    optionalUser.get().getLogin() + " - is already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userMapper.signUpToUserEntity(signUpDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserEntityFromAuthentication(Authentication authentication){
        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserEntity)){
            return Optional.empty();
        }

        return Optional.of((UserEntity) principal);

    }

    public Optional<UserAuthRoleEntity> getUserAuthRole(Authentication authentication){

        // TODO: 22.05.2023 Perhaps this method will need to be corrected, but this is after adding a full-fledged user role assignment system

        if (authentication.getAuthorities() != null){
            if (!(authentication.getPrincipal() instanceof UserEntity user)){
                return Optional.empty();
            }

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            List<UserAuthRoleEntity> userAuthRoles = userAuthRoleRepository.findUserAuthRoleEntitiesByUser(user)
                    .orElse(new ArrayList<>());

            for (UserAuthRoleEntity role : userAuthRoles){
                if (authorities.contains(new SimpleGrantedAuthority(role.getRole()))) {
                    return Optional.of(role);
                }
            }
        }

        return Optional.empty();
    }
}
