package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Url;
import com.example.tilproject.domain.UrlSection;
import com.example.tilproject.dto.UrlCreateDeleteDto;
import com.example.tilproject.dto.UrlModifyDto;
import com.example.tilproject.dto.UrlRequestDto;
import com.example.tilproject.dto.UrlTurnDto;

public class UrlMock {
    public static class Url1 {
        public static final Long IDX = 1L;
        public static final String URL_TURN = "1기";
        public static final String URL_NAME = "네이버";
        public static final String TO_URL_NAME = "다음";
        public static final String URL = "https://www.naver.com";
        public static final String TO_URL = "https://www.daum.net";
        public static final String TURN = "1기";
        public static final UrlSection URL_SECTION = UrlSection.TIMEATTACK;

        public static final Url sURL = Url.builder()
                .idx(IDX)
                .urlTurn(URL_TURN)
                .urlName(URL_NAME)
                .url(URL)
                .urlSection(URL_SECTION)
                .build();

        public static final UrlTurnDto URL_TURN_DTO = UrlTurnDto.builder()
                .url(URL)
                .urlName(URL_NAME)
                .urlSection(URL_SECTION)
                .build();

        public static final UrlRequestDto URL_REQUEST_DTO = UrlRequestDto.builder()
                .url(URL)
                .urlName(URL_NAME)
                .turn(URL_TURN)
                .urlSection(URL_SECTION)
                .build();

        public static final UrlModifyDto URL_MODIFY_DTO = UrlModifyDto.builder()
                .url(URL)
                .urlname(URL_NAME)
                .tourl(TO_URL)
                .tourlname(TO_URL_NAME)
                .turn("1기")
                .build();

        public static final UrlCreateDeleteDto URL_CREATE_DELETE_DTO = UrlCreateDeleteDto.builder()
                .url(URL)
                .urlName(URL_NAME)
                .turn(TURN)
                .urlSection(URL_SECTION)
                .build();
    }
}
