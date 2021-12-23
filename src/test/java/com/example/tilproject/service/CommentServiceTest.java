package com.example.tilproject.service;

import com.example.tilproject.domain.*;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.dto.NewCommentRequestDto;
import com.example.tilproject.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@Transactional
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    BoardRequestDto boardRequestDto;
    NewCommentRequestDto newCommentRequestDto;

    @Nested
    @DisplayName("댓글 삭제")
    class deleteComment {

        @Test
        @DisplayName("자신의 댓글 삭제")
        void successDelete(){
            //given
            Turn turn = new Turn("1기");
            User user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setBoardIdx(1L);
            boardRequestDto.setTitle("게시판 제목");
            boardRequestDto.setContent("게시판 내용");

            Board board = new Board(boardRequestDto, user);
            newCommentRequestDto = new NewCommentRequestDto(board.getBoardIdx(), "댓글 내용");
            Comment comment = new Comment(newCommentRequestDto, board, user);

            given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

            //when
            String bool = commentService.deleteComment(1L, user);

            //then
            assertEquals(bool, "success");
        }

        @Test
        @DisplayName("타인의의 댓글 삭제")
        void failDelete(){
            //given
            Turn turn = new Turn("1기");
            User user1 = new User("test1", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            User user2 = new User("test2", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setBoardIdx(1L);
            boardRequestDto.setTitle("게시판 제목");
            boardRequestDto.setContent("게시판 내용");

            Board board = new Board(boardRequestDto, user1);
            newCommentRequestDto = new NewCommentRequestDto(board.getBoardIdx(), "댓글 내용");
            Comment comment = new Comment(newCommentRequestDto, board, user1);

            given(commentRepository.findById(1L)).willReturn(Optional.of(comment));

            //when
            String bool = commentService.deleteComment(1L, user2);

            //then
            assertEquals(bool, "fail");
        }
    }
}