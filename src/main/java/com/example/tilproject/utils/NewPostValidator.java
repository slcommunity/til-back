package com.example.tilproject.utils;

import com.example.tilproject.domain.User;

public class NewPostValidator {

    public static void validateCreatePost(String title, String summary, String imageUrl, String postLink, User user) {
        if (title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException("제목이 없습니다.");
        }
        if (!URLValidator.urlValidator(imageUrl)) {
            throw new IllegalArgumentException("url 형식이 맞지 않습니다.");
        }
        if (!URLValidator.urlValidator(postLink)) {
            throw new IllegalArgumentException("url 형식이 맞지 않습니다.");
        }
        if (user == null) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
    }
}
