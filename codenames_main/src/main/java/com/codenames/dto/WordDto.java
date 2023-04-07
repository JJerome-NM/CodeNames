package com.codenames.dto;

import com.codenames.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class WordDto {

    private int id;

    private String text;

    private Color color;
}
