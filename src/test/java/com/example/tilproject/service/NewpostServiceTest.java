package com.example.tilproject.service;

import com.example.tilproject.domain.NewPost;
import com.example.tilproject.domain.User;
import com.example.tilproject.dto.SearchRequestDto;
import com.example.tilproject.dto.SearchResponse;
import com.example.tilproject.repository.adminRepository.NewPostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class NewpostServiceTest {

    @Mock //가짜객체
    NewPostRepository newPostRepository;

    @InjectMocks //내가쓸놈?
    NewpostService newpostService;

    @Test
    @DisplayName("검색")
    void postSearch() {
        //given
        User user = new User();
        SearchRequestDto searchRequestDto = new SearchRequestDto();
        searchRequestDto.setSearchtext("testtitle");
        searchRequestDto.setSelecter("");
        NewPost newpost = new NewPost("testtitle", "testsummary",  "http://img.com/img", "http://img.com/img", user);

        List<NewPost> search = new LinkedList<>();
        search.add(newpost);

        given(newPostRepository.findBytitleContainingOrderByIdxDesc("testtitle")).willReturn(search);
        given(newPostRepository.findBysummaryContainingOrderByIdxDesc("testtitle")).willReturn(search);

        //when
        List<SearchResponse> searchtitle = newpostService.postSearch(searchRequestDto);

        //then
        assertEquals("testtitle", searchtitle.get(0).getTitle());
        assertEquals("testsummary", searchtitle.get(0).getSummary());
        assertEquals("http://img.com/img", searchtitle.get(0).getImageUrl());
        assertEquals("http://img.com/img", searchtitle.get(0).getPostLink());
    }
}
