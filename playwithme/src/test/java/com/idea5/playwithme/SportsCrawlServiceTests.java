package com.idea5.playwithme;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class SportsCrawlServiceTests {

    SportsCrawlService sportsCrawlService;

    @Test
    public void 크롤링할_주소_입력_테스트_날짜(){
        sportsCrawlService.setUrl(2);

        String assertUrl = "https://sports.news.naver.com/kfootball/schedule/index?month=09&year=2022&teamCode=";
        assertThat(sportsCrawlService.getUrl()).isEqualTo(assertUrl);
    }
}
