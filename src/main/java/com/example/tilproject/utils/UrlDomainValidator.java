package com.example.tilproject.utils;

import com.example.tilproject.dto.UrlModifyDto;
import com.example.tilproject.dto.UrlRequestDto;
import org.apache.commons.validator.routines.UrlValidator;


public class UrlDomainValidator {

    public static UrlValidator urlValidator = new UrlValidator();

    public static void validateCreateUrl(UrlRequestDto urlRequestDto){
        if(!urlValidator.isValid(urlRequestDto.getUrl())){
            throw new IllegalArgumentException("Url 양식이 틀렸습니다.");
        }

        if(urlRequestDto.getUrlName() == null || urlRequestDto.getUrlName().trim().length() == 0){
            throw new IllegalArgumentException("Url 이름이 공백입니다.");
        }

        if(urlRequestDto.getTurn() == null || urlRequestDto.getTurn().trim().length() == 0){
            throw new IllegalArgumentException("Url 기수가 잘못됐습니다.");
        }

        if(urlRequestDto.getUrlSection() == null){
            throw new IllegalArgumentException("Url 구분이 잘못됐습니다.");
        }
    }

    public static void validateUpdateUrl(UrlModifyDto urlModifyDto){
        if(!urlValidator.isValid(urlModifyDto.getTourl())){
            throw new IllegalArgumentException("Url 양식이 틀렸습니다.");
        }
        if(urlModifyDto.getTourlname() == null || urlModifyDto.getTourlname().trim().length() == 0){
            throw new IllegalArgumentException("Url 이름이 공백입니다.");
        }
    }
}
