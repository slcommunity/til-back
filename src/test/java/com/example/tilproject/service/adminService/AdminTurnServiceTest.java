package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.dto.TurnsGetDto;
import com.example.tilproject.repository.adminRepository.TurnRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.example.tilproject.service.adminService.TurnMock.Turn1.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AdminTurnServiceTest {

    @Mock
    TurnRepository turnRepository;

    @InjectMocks
    AdminTurnService adminTurnService;


    @Test
    @DisplayName("기수 조회")
    void getTurns(){
        List<Turn> turns = new ArrayList<>();
        turns.add(TURN);
        given(turnRepository.findAll()).willReturn(turns);

        List<TurnsGetDto> turnsGetDtos = adminTurnService.getTurn();

        assertThat(turnsGetDtos.contains(TURNS_GET_DTO));
    }

    @Test
    @DisplayName("새로운 기수 추가")
    void newTurn() {
        given(turnRepository.findByTurn(TURN_NAME)).willReturn(TURN);

        assertThrows(IllegalArgumentException.class, () -> adminTurnService.createTurn(TURN_REQUEST_DTO));
    }

    @Test
    @DisplayName("기수 수정")
    void modifyTurn() {
        given(turnRepository.findByTurn(TURN_NAME)).willReturn(TURN);

        Long idx = adminTurnService.modifyTurn(TURN_MODIFY_DTO);

        assertThat(idx).isEqualTo(TURN.getIdx());
    }

    @Test
    @DisplayName("기수 삭제")
    void deleteTurn(){
        given(turnRepository.findByTurn(TURN_NAME)).willReturn(TURN);

        String result = adminTurnService.deleteTurn(TURN_NAME);

        then(turnRepository).should(times(2)).findByTurn(TURN_NAME);
    }

}