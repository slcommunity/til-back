package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import com.example.tilproject.dto.TurnsGetDto;

public class TurnMock {
    public static class Turn1{
        public static final Long IDX = 1L;
        public static final String TURN_NAME = "3기";
        public static final String NEW_TURN_NAME = "4기";

        public static final Turn TURN = Turn.builder()
                .idx(IDX)
                .turn(TURN_NAME)
                .build();

        public static final TurnRequestDto TURN_REQUEST_DTO = TurnRequestDto.builder()
                .turnName(TURN_NAME)
                .build();

        public static final TurnModifyDto TURN_MODIFY_DTO = TurnModifyDto.builder()
                .oldTurn(TURN_NAME)
                .newTurn(NEW_TURN_NAME)
                .build();

        public static final TurnsGetDto TURNS_GET_DTO = TurnsGetDto.builder()
                .turn(TURN_NAME)
                .build();
    }
}
