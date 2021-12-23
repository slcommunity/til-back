package com.example.tilproject.service;

import com.example.tilproject.repository.BoardRepository;
import com.example.tilproject.repository.CommentRepository;
import com.example.tilproject.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    @Mock
    UserRepository userRepository;

    @Mock
    BoardRepository boardRepository;

    @Test
    @DisplayName("댓글 저장")
    void setArticleComment() {
    }

    @Test
    @DisplayName("댓글 수정")
    void updateComment() {
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() {
    }

    @Test
    @DisplayName("내가 쓴 댓글")
    void getMyComments() {
    }
}