package com.codenames.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@RequiredArgsConstructor
@Getter @Setter
public class CredentialDto {

    @Email
    private String login;

    @Length(min = 8)
    private String password;
}
