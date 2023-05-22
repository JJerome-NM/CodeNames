package com.codenames.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserAuthDto {
    private int id;

    private String nickname;

    private String login;

    private String jwtToken;
}
