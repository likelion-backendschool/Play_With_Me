package com.idea5.playwithme.event.crawling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EsportsCrawlServiceTest {

    EsportsCrawlService esportsCrawlService;

    @Test
    public void 크롤링할_주소_입력_테스트_날짜(){
        esportsCrawlService.setUrl();

        String assertUrl = "https://game.naver.com/esports/schedule/lck?date=2022-09";
        System.out.println(assertUrl);
        assertThat(esportsCrawlService.getUrl()).isEqualTo(assertUrl);
    }
}