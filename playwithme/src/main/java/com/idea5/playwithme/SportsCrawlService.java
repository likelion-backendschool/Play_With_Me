package com.idea5.playwithme;

import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Service
public class SportsCrawlService {

    @Getter
    private static String url = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}";
    // 축구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query=2022%EB%85%84%208%EC%9B%94%2013%EC%9D%BC%20K%EB%A6%AC%EA%B7%B81%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95"
    // 농구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20%EB%86%8D%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95
    // 야구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20%EC%95%BC%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95

    public static void setUrl(int category) {

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1).plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String eventDate = nextMonth.format(formatter);

        switch (category){
            case 1 :
                url +="%EC%9D%BC%20%EC%95%BC%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95";
                break;

            case 2 :
                url += "%EC%9D%BC%20K%EB%A6%AC%EA%B7%B81%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95";
                break;

            case 3 :
                url += "%EC%9D%BC%20%EB%86%8D%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95";
                break;
        }
        url = url.replace("{dd}",eventDate.substring(6,8));
        url = url.replace("{mm}",eventDate.substring(4,6));
        url = url.replace("{yy}",eventDate.substring(0,4));
    }

    static void crawling() throws IOException {
        Connection conn = Jsoup.connect(url);

        System.out.println(url);
        Document document = conn.get();
        Elements tr = document.select("tbody._scroll_content tr");


        for(Element e : tr){
            String name = e.select("td.l_team a").attr("title")+" vs "+e.select("td.r_team a").attr("title");

            String place = e.select("td.place").attr("title") + " 경기장";

            System.out.println(name +" \n"+place);
        }
    }

    static void crawlingBaseBall() throws IOException {
        Connection conn = Jsoup.connect(url);

        Document document = conn.get();
        Elements tr = document.select("tbody._scroll_content tr");


        for(Element e : tr){
            String name = e.select("td.l_team a").attr("title")+" vs "+e.select("td.r_team a").attr("title");

            String place = e.select("td.place a").attr("title") + " 경기장";

            System.out.println(name +" \n"+place);
        }
    }

    public static void main(String[] args) throws IOException {
        setUrl(1);
        crawl(1);
    }

    public static void crawl(int category) throws IOException {
        if(category==1){
            crawlingBaseBall();
        }
        else{
            crawling();
        }
    }
}
