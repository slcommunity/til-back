package com.example.tilproject.domain;

import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Nested
    @DisplayName("자유게시판 객체 생성")
    class createBoard{
        private String title;
        private String content;
        private User user;
        private BoardRequestDto boardRequestDto;

        @BeforeEach
        void setup(){
            title = "제목1";
            content = "내용1";
            user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev", new Turn("1기"), "http://img.com/img");
            boardRequestDto = new BoardRequestDto();
        }

        @Test
        @DisplayName("정상 케이스")
        void create_Normal() {
            // given
            boardRequestDto.setTitle(title);
            boardRequestDto.setContent(content);
            // when
            Board board = new Board(boardRequestDto ,user);

            assertNull(board.getBoardIdx());
            assertEquals(title, board.getTitle());
            assertEquals(content, board.getContent());
            assertEquals(user, board.getUser());
        }

        @Nested
        @DisplayName("실패 케이스")
        class failCases {

            @Nested
            @DisplayName("자유게시판 제목")
            class Title {

                @Test
                @DisplayName("null")
                void fail_null() {
                    title = null;

                    boardRequestDto.setTitle(title);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new Board(boardRequestDto, user));

                    assertEquals(exception.getMessage(), "자유게시판 제목은 필수 입력사항 입니다.");
                }
            }

            @Nested
            @DisplayName("자유게시판 유저")
            class User {

                @Test
                @DisplayName("null")
                void fail_null() {
                    user = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new Board(boardRequestDto, user));

                    assertEquals(exception.getMessage(), "유저 정보가 유효하지 않습니다.");
                }
            }
        }
    }
    @Nested
    @DisplayName("피드 객체 수정")
    class Update {
        private String title;
        private String content;
        private User user;
        private BoardRequestDto boardRequestDto;

        private String updateTitle;
        private String updateContent;

        @BeforeEach
        void setup(){
            title = "제목1";
            content = "내용1";
            user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev", new Turn("1기"), "http://img.com/img");

            boardRequestDto = new BoardRequestDto();
            updateTitle = "수정 제목1";
            updateContent = "수정 내용1";
        }

        @Test
        @DisplayName("정상 케이스")
        void update_Normal() {
            boardRequestDto.setTitle(updateTitle);
            boardRequestDto.setContent(updateContent);

            Board board = new Board(boardRequestDto, user);

            board.update(boardRequestDto, user);
            // then
            assertNull(board.getBoardIdx());
            assertEquals(updateTitle, board.getTitle());
            assertEquals(updateContent, board.getContent());
            assertEquals(user, board.getUser());
        }

        @Nested
        @DisplayName("실패 케이스")
        class FailCases {

            @Nested
            @DisplayName("자유게시판 제목")
            class Title {

                @Test
                @DisplayName("null")
                void fail_null() {
                    boardRequestDto.setTitle(updateTitle);
                    boardRequestDto.setContent(updateContent);
                    Board board = new Board(boardRequestDto, user);
                    updateTitle = null;

                    boardRequestDto.setTitle(updateTitle);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> board.update(boardRequestDto, user));

                    assertEquals(exception.getMessage(), "자유게시판 제목은 필수 입력사항 입니다.");
                }
            }
        }
    }
}