package com.example.tilproject.domain;

import com.example.tilproject.dto.UrlModifyDto;
import com.example.tilproject.dto.UrlRequestDto;
import com.example.tilproject.utils.UrlDomainValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    @NotNull
    private String urlTurn;

    @Column(nullable = false)
    @NotNull
    private String urlName;

    @NotNull
    private String url;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private UrlSection urlSection;

    public Url(UrlRequestDto urlRequestDto) {
        UrlDomainValidator.validateCreateUrl(urlRequestDto);
        this.urlTurn = urlRequestDto.getTurn();
        this.urlName = urlRequestDto.getUrlName();
        this.url = urlRequestDto.getUrl();
        this.urlSection = urlRequestDto.getUrlSection();
    }

    public void update(UrlModifyDto urlModifyDto){
        UrlDomainValidator.validateUpdateUrl(urlModifyDto);
        this.url = urlModifyDto.getTourl();
        this.urlName = urlModifyDto.getTourlname();
    }
}
