package com.idea5.playwithme;

import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CrawlService {
    @Getter
    private static String url = "http://ticket.interpark.com/tiki/special/TPCalendar.asp?ImgYn=Y&Ca=&KindOfGoods={category}&KindOfFlag=&Type=A&PlayDate={eventDate}";

    private final static String musicalId = "01011";
    private final static String concertId = "01003";

    public static void setDate(){ // 매일 날짜 바꾸기 위해서
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        String eventDate = nextMonth.format(formatter);
        url = url.replace("{eventDate}",eventDate);
        url = url.replace("{category}",musicalId);
        System.out.println(url);
    }

    public static void crawling() throws IOException{
//        Connection conn = Jsoup.connect(DRIVER_PATH);
        Connection conn = Jsoup.connect("https://ticket.interpark.com/tiki/special/TPCalendar.asp?ImgYn=Y&Ca=&KindOfGoods=01009&KindOfFlag=P&PlayDate=20220805");

        List<String> names = crawlEventName();
        List<String> places = crawlEventPlace();
        System.out.println(names);
        System.out.println(places);
        System.out.println(names.size()+" "+places.size());
    }

    public static List<String> crawlEventName() throws IOException {
//        Connection conn = Jsoup.connect("https://ticket.interpark.com/tiki/special/TPCalendar.asp?ImgYn=Y&Ca=&KindOfGoods=01009&KindOfFlag=P&PlayDate=20220805");

        Connection conn = Jsoup.connect(url);

        Elements name = null;
        List<String> names = new ArrayList<>();

        Document document = conn.get();
        Elements table = document.select("table[width=726]");
        Elements tbody = table.select("tbody"); // tbody 선택

        name = tbody.select("td[width=300] a b");
        for(Element e: name){ // 찾은 Element에 대해서 List에 저장
            names.add(e.text());
        }


        return names;
    }

    public static List<String> crawlEventPlace() throws IOException {
//        Connection conn = Jsoup.connect("https://ticket.interpark.com/tiki/special/TPCalendar.asp?ImgYn=Y&Ca=&KindOfGoods=01009&KindOfFlag=P&PlayDate=20220805");

        Connection conn = Jsoup.connect(url);

        Elements place = null;
        List<String> places = new ArrayList<>();


        Document document = conn.get();
        Elements table = document.select("table[width=726]");
        Elements tbody = table.select("tbody"); // tbody 선택

        place = tbody.select("td[width=120] a");
        for(Element e: place){ // 찾은 Element에 대해서 List에 저장
            places.add(e.text());
        }

        return places;
    }

    public static void main(String[] args)throws IOException {
        setDate();
        crawling();
    }
}
