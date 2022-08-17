package com.idea5.playwithme.event.crawling;

import com.idea5.playwithme.event.service.crawling.SportsCrawlService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SportsCrawlServiceTests {

    SportsCrawlService sportsCrawlService;

    // 축구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20K%EB%A6%AC%EA%B7%B81%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95"
    // 농구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20%EB%86%8D%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95
    // 야구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20%EC%95%BC%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95


    @Test
    public void 크롤링할_주소_입력_테스트_날짜(){
        sportsCrawlService.setUrl(2);

        String assertUrl = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query=2022%EB%85%84%2009%EC%9B%94%2012%EC%9D%BC%20K%EB%A6%AC%EA%B7%B81%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95";
        System.out.println(assertUrl);
        assertThat(sportsCrawlService.getUrl()).isEqualTo(assertUrl);
    }
}
