package com.example.tilproject.domain;

import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.dto.CommentResponseDto;
import com.example.tilproject.dto.NewCommentRequestDto;
import com.example.tilproject.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Nested
    @DisplayName("댓글 객체 생성")
    class createComment{
        private String content;
        private User user;
        private Board board;
        private BoardRequestDto boardRequestDto;
        private NewCommentRequestDto commentRequestDto;

        @BeforeEach
        void setup(){
            user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev", new Turn("1기"), "http://img.com/img");
            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setTitle("제목");
            boardRequestDto.setContent("내용");
            Board board = new Board(boardRequestDto, user);
        }

        @Test
        @DisplayName("정상케이스")
        void createNormal() {
            //given
            content = "댓글 내용1";

            commentRequestDto = new NewCommentRequestDto(1L, content);
            //when
            Comment result = new Comment(commentRequestDto, board, user);

            //then
            assertNull(result.getIdx());
            assertEquals(content, result.getContent());
            assertEquals(board, result.getBoard());
            assertEquals(user, result.getUser());
        }

        @Nested
        @DisplayName("실패 케이스")
        class failCases {

            @Nested
            @DisplayName("댓글 내용")
            class Content {
                @Test
                @DisplayName("null")
                void failNull() {
                    //given
                    content = null;

                    commentRequestDto = new NewCommentRequestDto(1L, content);
                    //when
                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new Comment(commentRequestDto, board, user));

                    //then
                    assertEquals(exception.getMessage(), "댓글 내용은 필수 입력사항 입니다.");
                }

                @Test
                @DisplayName("공백")
                void failEmpty() {
                    //given
                    content = "";

                    commentRequestDto = new NewCommentRequestDto(1L, content);
                    //when
                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new Comment(commentRequestDto, board, user));

                    //then
                    assertEquals(exception.getMessage(), "댓글 내용은 필수 입력사항 입니다.");
                }
            }
        }

        @Nested
        @DisplayName("유저")
        class UserTest {

            @Test
            @DisplayName("유저 정보 없음")
            void failUser() {
                //given
                user = null;
                content = "댓글 내용1";
                commentRequestDto = new NewCommentRequestDto(1L, content);

                //when
                Exception exception = assertThrows(ApiRequestException.class,
                        () -> new Comment(commentRequestDto, board, user));

                //then
                assertEquals(exception.getMessage(), "유저 정보가 유효하지 않습니다.");
            }
        }
    }

}