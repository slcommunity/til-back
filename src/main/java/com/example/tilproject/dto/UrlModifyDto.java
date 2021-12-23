package com.example.tilproject.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlModifyDto {
    private String urlname;
    private String url;
    private String tourl;
    private String tourlname;
    private String turn;
}
