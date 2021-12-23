package com.example.tilproject.service;

import com.example.tilproject.domain.Board;
import com.example.tilproject.domain.Turn;
import com.example.tilproject.domain.User;
import com.example.tilproject.domain.UserRole;
import com.example.tilproject.dto.BoardRequestDto;
import com.example.tilproject.dto.BoardResponseDto;
import com.example.tilproject.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Transactional
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    BoardRepository boardRepository;

    @InjectMocks
    BoardService boardService;

    BoardRequestDto boardRequestDto;
    BoardRequestDto updateBoard;
    BoardResponseDto boardResponseDto;

    @Nested
    @DisplayName("자유게시판 게시물")
    class SuccessCases {


        @Test
        @DisplayName("자유게시판 게시물 저장")
        void setBoard() {
            //given
            Turn turn = new Turn("1기");
            User user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setBoardIdx(1L);
            boardRequestDto.setTitle("게시판 제목");
            boardRequestDto.setContent("게시판 내용");

            Board board = new Board(boardRequestDto, user);

            given(boardRepository.save(any(Board.class))).willReturn(board);

            //when
            Board result = boardService.SetBoard(boardRequestDto, user);

            //then
            assertEquals(result.getUser(), user);
            assertEquals(result.getBoardIdx(), 1L);
            assertEquals(result.getTitle(), "게시판 제목");
            assertEquals(result.getContent(), "게시판 내용");
        }

        @Test
        @DisplayName("자유게시판 게시물 수정 성공")
        void updateBoardSuccess() {
            //given
            Turn turn = new Turn("1기");
            User user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setBoardIdx(1L);
            boardRequestDto.setTitle("게시판 제목");
            boardRequestDto.setContent("게시판 내용");

            updateBoard = new BoardRequestDto();
            updateBoard.setBoardIdx(1L);
            updateBoard.setTitle("게시판 제목 수정");
            updateBoard.setContent("게시판 내용 수정");

            Board board = new Board(boardRequestDto, user);

            given(boardRepository.findById(1L)).willReturn(Optional.of(board));

            //when
            String bool = boardService.updateBoard(1L, updateBoard, user);
            Board result = boardService.getBoard(1L);

            //then
            assertEquals(bool, "success");
            assertEquals(result.getTitle(), "게시판 제목 수정");
            assertEquals(result.getContent(), "게시판 내용 수정");
        }

        @Test
        @DisplayName("자유게시판 게시물 수정 실패")
        void updateBoardFail() {
            //given
            Turn turn = new Turn("1기");
            User user1 = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            User user2 = new User("test2", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setBoardIdx(1L);
            boardRequestDto.setTitle("게시판 제목");
            boardRequestDto.setContent("게시판 내용");

            updateBoard = new BoardRequestDto();
            updateBoard.setBoardIdx(1L);
            updateBoard.setTitle("게시판 제목 수정");
            updateBoard.setContent("게시판 내용 수정");

            Board board = new Board(boardRequestDto, user1);

            given(boardRepository.findById(1L)).willReturn(Optional.of(board));

            //when
            String bool = boardService.updateBoard(1L, updateBoard, user2);
            Board result = boardService.getBoard(1L);

            //then
            assertEquals(bool, "fail");
            assertEquals(result.getTitle(), "게시판 제목");
            assertEquals(result.getContent(), "게시판 내용");
        }

        @Test
        @DisplayName("내가 쓴 게시물")
        void myBoards() {
            //given
            Turn turn = new Turn("1기");
            User user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");

            boardRequestDto = new BoardRequestDto();
            boardRequestDto.setBoardIdx(1L);
            boardRequestDto.setTitle("게시판 제목");
            boardRequestDto.setContent("게시판 내용");

            Board board1 = new Board(boardRequestDto, user);

            board1.setCreatedAt(LocalDateTime.now());
            List<Board> boardList = new LinkedList<>();
            boardList.add(board1);

            given(boardRepository.findByUser(user)).willReturn(boardList);

            //when
            List<BoardResponseDto> result = boardService.searchBoards(user);

            //then
            assertEquals(result.size(), 1);
        }
    }
}