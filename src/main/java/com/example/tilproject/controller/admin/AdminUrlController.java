package com.example.tilproject.controller.admin;

import com.example.tilproject.domain.UrlSection;
import com.example.tilproject.dto.UrlCreateDeleteDto;
import com.example.tilproject.dto.UrlModifyDto;
import com.example.tilproject.dto.UrlRequestDto;
import com.example.tilproject.dto.UrlTurnDto;
import com.example.tilproject.service.adminService.AdminUrlService;
import com.example.tilproject.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminUrlController {

    private final AdminUrlService adminUrlService;

    //공지 조회
    @GetMapping("/admin/url/{turn}")
    public Result getUrl(@PathVariable String turn) {
        List<UrlTurnDto> urls = adminUrlService.getUrl(turn);
        return new Result(urls);
    }

    //공지수정
    @Secured("ROLE_ADMIN")
    @PutMapping("/admin/url")
    public String updateUrl(@ModelAttribute UrlModifyDto urlModifyDto){
        return adminUrlService.modifyUrl(urlModifyDto);
    }

    //공지 작성
    @Secured("ROLE_ADMIN")
    @PostMapping("/admin/url")
    public String createUrl(@ModelAttribute UrlRequestDto urlRequestDto){
        return adminUrlService.createUrl(urlRequestDto);
    }

    //공지 삭제
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/admin/url")
    public String deleteUrl(@ModelAttribute UrlCreateDeleteDto urlCreateDeleteDto){
        return adminUrlService.deleteUrl(urlCreateDeleteDto);
    }

}
