package com.example.tilproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NewpostTest {

    @Nested
    @DisplayName("객체 생성")
    class CreatePost {
        private String title;
        private String summary;
        private String imageUrl;
        private String postLink;
        private User user;

        @BeforeEach
        void setup() {
            title = "testtitle";
            summary = "testsummary";
            imageUrl = "http://img.com/img";
            postLink = "http://img.com/img";
            Turn turn = new Turn("1기");
             user = new User("test", "테스트", "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                    turn, "https://img.com/img");
        }

        @Test
        @DisplayName("정상케이스")
        void create_Normal() {
            //given

            //when
            NewPost newpost = new NewPost(title, summary, imageUrl, postLink, user);
            //then
            assertThat(newpost.getTitle()).isEqualTo(title);
            assertThat(newpost.getSummary()).isEqualTo(summary);
            assertThat(newpost.getImageUrl()).isEqualTo(imageUrl);
            assertThat(newpost.getPostLink()).isEqualTo(postLink);
            assertThat(newpost.getUser()).isEqualTo(user);
            }

        @Nested
        @DisplayName("실패케이스")
        class FailCases {

            @Nested
            @DisplayName("제목")
            class title {

                @Test
                @DisplayName("null")
                void fail_null() {
                    //given
                    title = null;
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new NewPost(title, summary, imageUrl, postLink, user));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("제목이 없습니다.");
                }

                @Test
                @DisplayName("공백")
                void fail_empty() {
                    //given
                    title = "";
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new NewPost(title, summary, imageUrl, postLink, user));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("제목이 없습니다.");
                }
            }
            @Nested
            @DisplayName("이미지")
            class imageUrl {

                @Test
                @DisplayName("url 형식")
                void fail_url_from() {
                    //given
                    imageUrl = "1234";
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new NewPost(title, summary, imageUrl, postLink, user));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("url 형식이 맞지 않습니다.");
                }
            }
            @Nested
            @DisplayName("링크")
            class postLink {

                @Test
                @DisplayName("url 형식")
                void fail_url_from() {
                    //given
                    postLink = "1234";
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new NewPost(title, summary, imageUrl, postLink, user));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("url 형식이 맞지 않습니다.");
                }
            }

            @Nested
            @DisplayName("user")
            class user {

                @Test
                @DisplayName("null")
                void fail_null() {
                    //given
                    user = null;
                    //when
                    Exception exception = assertThrows(IllegalArgumentException.class,
                            () -> new NewPost(title, summary, imageUrl, postLink, user));
                    //then
                    assertThat(exception.getMessage()).isEqualTo("사용자가 없습니다.");
                }
            }
        }
    }
}
