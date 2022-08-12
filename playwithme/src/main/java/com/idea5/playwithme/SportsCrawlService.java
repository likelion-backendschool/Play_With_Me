package com.idea5.playwithme;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Service
public class SportsCrawlService {

    @Getter
    private static String url = "https://sports.news.naver.com/{category}/schedule/index?month={month}&year={year}&teamCode=";
    // 축구 url : https://sports.news.naver.com/kfootball/schedule/index?month=11&year=2022&teamCode=
    // 농구 url : https://sports.news.naver.com/basketball/schedule/index?month=11&year=2022&teamCode=
    // 야구 url : https://sports.news.naver.com/kbaseball/schedule/index?month=11&year=2022&teamCode=


    public static void setUrl(int category) {

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String eventDate = nextMonth.format(formatter);

        switch (category){
            case 1 :
                url = url.replace("{category}","kbaseball");
                break;

            case 2 :
                url = url.replace("{category}","kfootball");
                break;

            case 3 :
                url = url.replace("{category}","basketball");
                break;
        }

        url = url.replace("{month}",eventDate.substring(4,6));
        url = url.replace("{year}",eventDate.substring(0,4));
    }

    void crawling(){

    }
}
