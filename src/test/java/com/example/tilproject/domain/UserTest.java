package com.example.tilproject.domain;

import com.example.tilproject.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Nested
    @DisplayName("유저 객체 생성")
    class CreateUser {

        private String username;
        private String password;
        private UserRole role;
        private String name;
        private String blog;
        private String github;
        private String image;
        private Turn turn;

        @BeforeEach
        void setup() {
            username = "test";
            password = "1234";
            role = UserRole.USER;
            name = "테스트";
            blog = "https://l0u0l.tistory.com/";
            github = "https://github.com/chanhong-dev";
            image = "http://img.com/img";
            turn = new Turn("1기");
        }

        @Test
        @DisplayName("정상 케이스")
        void createUser_Normal() {
            User user = new User( username,  name,  password,  role,  blog,  github,  turn,  image);

            assertEquals(username, user.getUsername());
            assertEquals(name, user.getName());
            assertEquals(password, user.getPassword());
            assertEquals(role, user.getRole());
            assertEquals(blog, user.getBlog());
            assertEquals(github, user.getGithub());
            assertEquals(turn, user.getTurn());
            assertEquals(image, user.getImage());
        }

        @Nested
        @DisplayName("실패 케이스")
        class Failcases {

            @Nested
            @DisplayName("username")
            class Username {

                @Test
                @DisplayName("null")
                void fail_null() {
                    username = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "회원 Id 가 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("password")
            class Password {

                @Test
                @DisplayName("null")
                void fail_null() {
                    password = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "회원 비밀번호 가 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("name")
            class Name {

                @Test
                @DisplayName("null")
                void fail_null() {
                    name = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "회원 이름 이 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("blog")
            class Blog {

                @Test
                @DisplayName("null")
                void fail_null() {
                    blog = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "블로그 링크 가 유효하지 않습니다.");
                }

                @Test
                @DisplayName("URL 형식")
                void fail_url_form() {
                    blog = "test_blog";

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "블로그 링크 가 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("github")
            class Github {

                @Test
                @DisplayName("null")
                void fail_null() {
                    github = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "깃헙 링크 가 유효하지 않습니다.");
                }

                @Test
                @DisplayName("URL 형식")
                void fail_url_form() {
                    github = "test_github";

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "깃헙 링크 가 유효하지 않습니다.");
                }
            }

//            @Nested
//            @DisplayName("image")
//            class Image {
//
//                @Test
//                @DisplayName("URL 형식")
//                void fail_url_form() {
//                    image = "test_image";
//
//                    Exception exception = assertThrows(ApiRequestException.class,
//                            () -> new User(username, name, password, role, blog, github, turn, image));
//
//                    assertEquals(exception.getMessage(), "이미지 링크 가 유효하지 않습니다.");
//                }
//            }

            @Nested
            @DisplayName("turn")
            class Turn_test {

                @Test
                @DisplayName("null")
                void fail_null() {
                    turn = null;

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> new User(username, name, password, role, blog, github, turn, image));

                    assertEquals(exception.getMessage(), "회원 기수정보 가 유효하지 않습니다.");
                }
            }
        }
    }

    @Nested
    @DisplayName("유저 객체 수정")
    class UpdateUser {
        private String username;
        private String password;
        private UserRole role;
        private String name;
        private String blog;
        private String github;
        private String image;
        private Turn turn;

        private String updatePassword;
        private String updateName;
        private String updateBlog;
        private String updateImage;
        private String updateGithub;

        @BeforeEach
        void setup() {
            username = "test";
            password = "1234";
            role = UserRole.USER;
            name = "테스트";
            blog = "https://l0u0l.tistory.com/";
            github = "https://github.com/chanhong-dev";
            image = "http://img.com/img";
            turn = new Turn("1기");

            updatePassword = "update1234";
            updateBlog = "https://update.tistory.com/";
            updateGithub = "https://github.com/update";
            updateName = "업데이트";
            updateImage = "http://img.com/update";
        }

        @Test
        @DisplayName("정상 케이스")
        void createUser_Normal() {
            User user = new User( username,  name,  password,  role,  blog,  github,  turn,  image);

            user.updateName(updateName);
            user.updateBlog(updateBlog);
            user.updateGithub(updateGithub);
            user.updatePassword(updatePassword);
            user.updateImage(updateImage);

            assertEquals(username, user.getUsername());
            assertEquals(updateName, user.getName());
            assertEquals(updatePassword, user.getPassword());
            assertEquals(role, user.getRole());
            assertEquals(updateBlog, user.getBlog());
            assertEquals(updateGithub, user.getGithub());
            assertEquals(turn, user.getTurn());
            assertEquals(updateImage, user.getImage());
        }

        @Nested
        @DisplayName("실패 케이스")
        class Failcases {

            @Nested
            @DisplayName("name")
            class Username {
                @Test
                @DisplayName("null")
                void fail_null() {
                    updateName = null;

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updateName(updateName));

                    assertEquals(exception.getMessage(), "회원 이름 이 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("password")
            class Password {

                @Test
                @DisplayName("null")
                void fail_null() {
                    updatePassword = null;

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updatePassword(updatePassword));

                    assertEquals(exception.getMessage(), "회원 비밀번호 가 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("blog")
            class Blog {

                @Test
                @DisplayName("null")
                void fail_null() {
                    updateBlog = null;

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updateBlog(updateBlog));

                    assertEquals(exception.getMessage(), "블로그 링크 가 유효하지 않습니다.");
                }

                @Test
                @DisplayName("URL 형식")
                void fail_url_form() {
                    updateBlog = "test_blog";

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updateBlog(updateBlog));

                    assertEquals(exception.getMessage(), "블로그 링크 가 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("github")
            class Github {

                @Test
                @DisplayName("null")
                void fail_null() {
                    updateGithub = null;

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updateGithub(updateGithub));

                    assertEquals(exception.getMessage(), "깃헙 링크 가 유효하지 않습니다.");
                }

                @Test
                @DisplayName("URL 형식")
                void fail_url_form() {
                    updateGithub = "test_github";

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updateGithub(updateGithub));

                    assertEquals(exception.getMessage(), "깃헙 링크 가 유효하지 않습니다.");
                }
            }

            @Nested
            @DisplayName("image")
            class Image {

                @Test
                @DisplayName("URL 형식")
                void fail_url_form() {
                    updateImage = "test_image";

                    User user = new User(username, name, password, role, blog, github, turn, image);

                    Exception exception = assertThrows(ApiRequestException.class,
                            () -> user.updateImage(updateImage));

                    assertEquals(exception.getMessage(), "이미지 링크 가 유효하지 않습니다.");
                }
            }
        }
    }
}