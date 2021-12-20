package com.example.tilproject.utils;

import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;

public class TurnValidator {
    public static void validateTurnCreate(TurnRequestDto turnRequestDto){
        if (turnRequestDto.getTurnName() == null || turnRequestDto.getTurnName().isEmpty()) {
            throw new IllegalArgumentException("저장할 기수명이 공백입니다.");
        }
    }

    public static void validateTurnModify(TurnModifyDto turnModifyDto){
        if (turnModifyDto.getNewTurn() == null || turnModifyDto.getNewTurn().isEmpty()) {
            throw new IllegalArgumentException("새로운 기수명이 공백입니다.");
        }
    }
}
