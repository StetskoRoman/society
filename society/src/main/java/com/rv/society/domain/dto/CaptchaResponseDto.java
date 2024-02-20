package com.rv.society.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CaptchaResponseDto {

//    из https://developers.google.com/recaptcha/docs/v3?hl=en&%3Bauthuser=19&authuser=19  там описание что должен вернуть гугл на запрос ??

    private boolean success;
    //    возвращаться будут названия с дефисом которые джава не поддерживает, поэтому  @JsonAlias("error-codes")
    @JsonAlias("error-codes")
    private Set<String> errorCodes;


}
