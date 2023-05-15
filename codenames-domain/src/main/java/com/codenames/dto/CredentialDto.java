package com.codenames.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
public class CredentialDto {

    private String login;

    private String password;
}
