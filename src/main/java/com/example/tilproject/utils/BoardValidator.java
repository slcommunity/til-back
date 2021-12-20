package com.example.tilproject.utils;

import com.example.tilproject.domain.User;
import com.example.tilproject.exception.ApiRequestException;

public class BoardValidator {

    public static void validateBoardCreate(String title, String content, User user){
        if(user == null){
            throw new ApiRequestException("유저 정보가 유효하지 않습니다.");
        }
        if(title == null || title.trim().length() == 0){
            throw new ApiRequestException("자유게시판 제목은 필수 입력사항 입니다.");
        }
    }
}
