package com.example.tilproject.utils;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.domain.UserRole;
import com.example.tilproject.exception.ApiRequestException;

public class UserValidator {
    public static void validateCreateUser(String username, String name, String password, UserRole role, String blog, String github, Turn turn, String image){
        if (username == null) {
            throw new ApiRequestException("회원 Id 가 유효하지 않습니다.");
        }
        if (password == null) {
            throw new ApiRequestException("회원 비밀번호 가 유효하지 않습니다.");
        }
        if (name == null) {
            throw new ApiRequestException("회원 이름 이 유효하지 않습니다.");
        }
        if (role == null) {
            throw new ApiRequestException("회원 권한 이 유효하지 않습니다.");
        }
        if (!URLValidator.urlValidator(blog)) {
            throw new ApiRequestException("블로그 링크 가 유효하지 않습니다.");
        }
        if (!URLValidator.urlValidator(github)) {
            throw new ApiRequestException("깃헙 링크 가 유효하지 않습니다.");
        }
//        if (!URLValidator.urlValidator(image)) {
//            throw new ApiRequestException("이미지 링크 가 유효하지 않습니다.");
//        }
        if (turn == null) {
            throw new ApiRequestException("회원 기수정보 가 유효하지 않습니다.");
        }
    }

    public static void validateUpdateName (String name){
        if (name == null) {
            throw new ApiRequestException("회원 이름 이 유효하지 않습니다.");
        }
    }

    public static void validateUpdateBlog (String blog){
        if (!URLValidator.urlValidator(blog)) {
            throw new ApiRequestException("블로그 링크 가 유효하지 않습니다.");
        }
    }

    public static void validateUpdateGithub (String github){
        if (!URLValidator.urlValidator(github)) {
            throw new ApiRequestException("깃헙 링크 가 유효하지 않습니다.");
        }
    }

    public static void validateUpdatePassword (String password){
        if (password == null) {
            throw new ApiRequestException("회원 비밀번호 가 유효하지 않습니다.");
        }
    }

    public static void validateUpdateImage (String image){
        if (!URLValidator.urlValidator(image)) {
            throw new ApiRequestException("이미지 링크 가 유효하지 않습니다.");
        }
    }
}
