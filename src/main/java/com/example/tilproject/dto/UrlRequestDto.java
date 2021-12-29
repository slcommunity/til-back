package com.example.tilproject.dto;

import com.example.tilproject.domain.UrlSection;
import lombok.*;

@Builder
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
