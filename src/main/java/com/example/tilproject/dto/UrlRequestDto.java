package com.example.tilproject.dto;

import com.example.tilproject.domain.UrlSection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequestDto {
    private String url;
    private String urlName;
    private String turn;
    private UrlSection urlSection;
}
