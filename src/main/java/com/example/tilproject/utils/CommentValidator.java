package com.example.tilproject.utils;

import com.example.tilproject.domain.User;
import com.example.tilproject.exception.ApiRequestException;

public class CommentValidator {

    public static void validateCommentCreate(String content, User user){
        if(user == null) {
            throw new ApiRequestException("유저 정보가 유효하지 않습니다.");
        }
        if(content == null || content.length() == 0) {
            throw new ApiRequestException("댓글 내용은 필수 입력사항 입니다.");
        }
    }

}
