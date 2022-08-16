package com.idea5.playwithme.event.crawling;


import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EsportsCrawlService {

    @Getter
    private static String url = "https://game.naver.com/esports/schedule/lck?date={yy}-{mm}";

    public static void setUrl() {

        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1).plusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String eventDate = nextMonth.format(formatter);

        url = url.replace("{mm}",eventDate.substring(4,6));
        url = url.replace("{yy}",eventDate.substring(0,4));
    }

    static void crawling() throws IOException {
        Connection conn = Jsoup.connect(url);

        System.out.println(url);
        Document document = conn.get();

    }

    public static void main(String[] args) throws IOException {
        setUrl();
        crawling();
    }
}
