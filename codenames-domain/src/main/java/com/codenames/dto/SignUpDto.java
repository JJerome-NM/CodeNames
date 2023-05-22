package com.codenames.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class SignUpDto {

    @Email
    private String email;

    @Size(min = 4, max = 20)
    private String nickname;

    @Size(min = 8, max = 50)
    private String password;
}
