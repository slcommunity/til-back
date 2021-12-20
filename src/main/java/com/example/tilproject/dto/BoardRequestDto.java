package com.example.tilproject.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BoardRequestDto {
    private Long boardIdx;
    private String title;
    private String content;
}