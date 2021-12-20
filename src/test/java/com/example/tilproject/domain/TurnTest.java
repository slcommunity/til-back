package com.example.tilproject.domain;


import com.example.tilproject.dto.TurnModifyDto;
import com.example.tilproject.dto.TurnRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TurnTest {

    @Nested
    @DisplayName("Turn 생성")
    class CreateTurn{

        private Long idx;
        private String turnName;

        @BeforeEach
        void setup(){

            turnName = "1기";
        }

        @Test
        @DisplayName("정상 케이스")
        void normalCreateTurn(){
            //given
            TurnRequestDto requestDto = new TurnRequestDto(turnName);
            //when
            Turn turn = new Turn(requestDto);
            //then
            assertEquals(turnName, turn.getTurn());
        }

        @Nested
        @DisplayName("실패 케이스")
        class failCases{

            @Nested
            @DisplayName("Turn 내용")
            class turnName{

                @Test
                @DisplayName("null")
                void turnNull(){
                    //given
                    turnName = null;
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Turn(new TurnRequestDto(turnName)));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("저장할 기수명이 공백입니다.");
                }

                @Test
                @DisplayName("공백")
                void turnEmpty(){
                    //given
                    turnName = "";
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Turn(new TurnRequestDto(turnName)));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("저장할 기수명이 공백입니다.");
                }
            }
        }

    }
    @Nested
    @DisplayName("Turn 수정")
    class turnUpdate{
        private String newTurnName;
        private String turnName;
        private Turn turn;

        @BeforeEach
        void setup(){
            newTurnName = "2기";
            turnName = "1기";
            turn = new Turn(new TurnRequestDto(turnName));
        }

        @Test
        @DisplayName("정상 케이스")
        void normalUpdateTurn(){
            //given
            TurnModifyDto turnModifyDto = new TurnModifyDto(turnName, newTurnName);
            //when
            turn.update(turnModifyDto);
            //then
            assertThat(turn.getTurn()).isEqualTo(newTurnName);
        }

        @Nested
        @DisplayName("실패 케이스")
        class failCases{

            @Nested
            @DisplayName("Turn 내용")
            class turnName{

                @Test
                @DisplayName("null")
                void turnNull(){
                    //given
                    newTurnName = null;
                    TurnModifyDto turnModifyDto = new TurnModifyDto(turnName, newTurnName);
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> turn.update(turnModifyDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("새로운 기수명이 공백입니다.");
                }

                @Test
                @DisplayName("공백")
                void turnEmpty(){
                    //given
                    newTurnName = "";
                    TurnModifyDto turnModifyDto = new TurnModifyDto(turnName, newTurnName);
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> turn.update(turnModifyDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("새로운 기수명이 공백입니다.");
                }
            }
        }
    }
}