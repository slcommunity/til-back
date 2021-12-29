package com.example.tilproject.domain;

import com.example.tilproject.dto.UrlModifyDto;
import com.example.tilproject.dto.UrlRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UrlTest {

    @Nested
    @DisplayName("Url 생성")
    class CreateUrl{

        private String urlTurn;
        private String urlName;
        private String url;
        private UrlSection urlSection;

        @BeforeEach
        void setup(){
            urlTurn = "1기";
            urlName = "네이버";
            url = "https://www.naver.com/";
            urlSection = UrlSection.PRESENTATION;
        }

        @Test
        @DisplayName("정상 케이스")
        void normalCreateUrl(){
            //given
            UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);
            //when
            Url instantUrl = new Url(urlRequestDto);
            //then
            assertEquals(urlName, instantUrl.getUrlName());
            assertEquals(url, instantUrl.getUrl());
            assertEquals(urlTurn, instantUrl.getUrlTurn());
            assertEquals(urlSection, instantUrl.getUrlSection());
        }

        @Nested
        @DisplayName("실패 케이스")
        class failCases{

            @Nested
            @DisplayName("Url 내용")
            class url{

                @Test
                @DisplayName("공백")
                void fail_empty(){
                    //given
                    url = "";
                    UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);

                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Url(urlRequestDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("Url 양식이 틀렸습니다.");
                }
            }

            @Nested
            @DisplayName("UrlName 내용")
            class urlName{

                @Test
                @DisplayName("공백")
                void fail_empty(){
                    //given
                    urlName = "";
                    UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);

                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Url(urlRequestDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("Url 이름이 공백입니다.");
                }
            }

            @Nested
            @DisplayName("UrlTurn 내용")
            class urlTurn{

                @Test
                @DisplayName("공백")
                void fail_empty(){
                    //given
                    urlTurn = "";
                    UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);

                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new Url(urlRequestDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("Url 기수가 잘못됐습니다.");
                }
            }
        }
    }
    @Nested
    @DisplayName("Url 수정")
    class modifyUrl{

        private String urlTurn;
        private String urlName;
        private String url;
        private UrlSection urlSection;

        private String toUrlName;
        private String toUrl;

        @BeforeEach
        void setup(){
            urlTurn = "1기";
            urlName = "네이버";
            url = "https://www.naver.com/";
            urlSection = UrlSection.PRESENTATION;

            toUrl = "https://www.daum.net/";
            toUrlName = "다음";
        }
        @Test
        @DisplayName("정상 케이스")
        void normalUpdateUrl(){
            //given
            UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);
            UrlModifyDto urlModifyDto = new UrlModifyDto(urlName, url, toUrl, toUrlName, urlTurn);
            //when
            Url instantUrl = new Url(urlRequestDto);
            instantUrl.update(urlModifyDto);
            //then
            assertEquals(toUrl, instantUrl.getUrl());
            assertEquals(toUrlName, instantUrl.getUrlName());
            assertEquals(urlTurn, instantUrl.getUrlTurn());
            assertEquals(urlSection, instantUrl.getUrlSection());
        }

        @Nested
        @DisplayName("실패 케이스")
        class failCases{

            @Nested
            @DisplayName("Url 내용")
            class url{

                @Test
                @DisplayName("공백")
                void fail_empty(){
                    //given
                    UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);
                    Url instantUrl = new Url(urlRequestDto);
                    toUrl = "";
                    UrlModifyDto urlModifyDto = new UrlModifyDto(urlName, url, toUrl, toUrlName, urlTurn);
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> instantUrl.update(urlModifyDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("Url 양식이 틀렸습니다.");
                }
            }

            @Nested
            @DisplayName("UrlName 내용")
            class urlName{

                @Test
                @DisplayName("공백")
                void fail_empty(){
                    //given
                    UrlRequestDto urlRequestDto = new UrlRequestDto(url, urlName, urlTurn, urlSection);
                    Url instantUrl = new Url(urlRequestDto);
                    toUrlName = "";
                    UrlModifyDto urlModifyDto = new UrlModifyDto(urlName, url, toUrl, toUrlName, urlTurn);
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> instantUrl.update(urlModifyDto));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("Url 이름이 공백입니다.");
                }
            }

        }
    }
}