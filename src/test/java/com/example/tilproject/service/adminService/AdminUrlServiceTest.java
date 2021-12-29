package com.example.tilproject.service.adminService;

import com.example.tilproject.domain.Url;
import com.example.tilproject.dto.UrlTurnDto;
import com.example.tilproject.repository.adminRepository.UrlRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.tilproject.service.adminService.UrlMock.Url1.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AdminUrlServiceTest {

    @Mock
    UrlRepository urlRepository;

    @InjectMocks
    AdminUrlService adminUrlService;

    @Test
    @DisplayName("Url 조회")
    void getUrl(){
        List<Url> urls = new ArrayList<>();
        urls.add(sURL);

        given(urlRepository.findAllByUrlTurn(URL_TURN)).willReturn(urls);

        List<UrlTurnDto> urlTurnDtos = adminUrlService.getUrl(URL_TURN);

        assertThat(urlTurnDtos.contains(URL_TURN_DTO));
    }

    @Test
    @DisplayName("Url 추가")
    void createUrl(){
        given(urlRepository.findByUrlAndUrlNameAndUrlTurn(URL, URL_NAME, URL_TURN)).willReturn(Optional.empty());

        String result = adminUrlService.createUrl(URL_REQUEST_DTO);

        assertThat(result).isEqualTo(URL_NAME);
    }

    @Test
    @DisplayName("Url 수정")
    void modifyUrl(){
        given(urlRepository.findByUrlAndUrlNameAndUrlTurn(URL, URL_NAME, URL_TURN)).willReturn(Optional.of(sURL));

        String result = adminUrlService.modifyUrl(URL_MODIFY_DTO);

        assertThat(result).isEqualTo(URL_TURN);
    }

    @Test
    @DisplayName("Url 삭제")
    void deleteUrl(){
        given(urlRepository.findByUrlAndUrlNameAndUrlTurn(URL, URL_NAME, URL_TURN)).willReturn(Optional.of(sURL));

        String result = adminUrlService.deleteUrl(URL_CREATE_DELETE_DTO);

        then(urlRepository).should(times(2)).findByUrlAndUrlNameAndUrlTurn(URL, URL_NAME, URL_TURN);
    }
}