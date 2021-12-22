package com.example.tilproject.service;

import com.example.tilproject.domain.Turn;
import com.example.tilproject.domain.User;
import com.example.tilproject.domain.UserRole;
import com.example.tilproject.dto.SignupRequestDto;
import com.example.tilproject.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@Transactional
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("유저 검색")
    void searchUser() {
        //Given
        String searchUsername = "test";
        Turn turn = new Turn("1기");
        User user = new User("test", "테스트",  "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                turn, "https://img.com/img");

        given(userRepository.findByUsername(searchUsername)).willReturn(Optional.of(user));
        //when

        User searchUser = userService.searchUser(searchUsername);

        //then
        assertEquals("test", searchUser.getUsername());
        assertEquals("테스트", searchUser.getName());
        assertEquals("1234", searchUser.getPassword());
        assertEquals(UserRole.USER, searchUser.getRole());
        assertEquals("https://l0u0l.tistory.com/", searchUser.getBlog());
        assertEquals("https://github.com/chanhong-dev", searchUser.getGithub());
        assertEquals(turn, searchUser.getTurn());
        assertEquals("https://img.com/img", searchUser.getImage());
    }

    @Test
    @DisplayName("유저 정보 수정")
    void updateUser() {
        Turn turn = new Turn("1기");
        User user = new User("test", "테스트",  "1234", UserRole.USER, "https://l0u0l.tistory.com/", "https://github.com/chanhong-dev",
                turn, "https://img.com/img");

        given(userRepository.findByUsername("test")).willReturn(Optional.of(user));

        //when
        user.update("test", "테스트 수정", "12345", "https://l0u0l.tistory.com", "https://github.com/chanhong-dev/", "https://img.com/update");

        User updateUser = userRepository.findByUsername("test").orElse(null);

        //then
        assertEquals("test", updateUser.getUsername());
        assertEquals("테스트 수정", updateUser.getName());
        assertEquals("12345", updateUser.getPassword());
        assertEquals(UserRole.USER, updateUser.getRole());
        assertEquals("https://l0u0l.tistory.com", updateUser.getBlog());
        assertEquals("https://github.com/chanhong-dev/", updateUser.getGithub());
        assertEquals(turn, updateUser.getTurn());
        assertEquals("https://img.com/update", updateUser.getImage());
    }
}