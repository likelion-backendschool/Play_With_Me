package com.idea5.playwithme.event.service.crawling;

import com.idea5.playwithme.board.domain.Board;
import com.idea5.playwithme.board.repository.BoardRepository;
import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.repository.EventRepository;
import lombok.Getter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SportsCrawlService implements CrawlService{

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BoardRepository boardRepository;
    @Getter
    private static String url = "";
    // 축구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query=2022%EB%85%84%208%EC%9B%94%2013%EC%9D%BC%20K%EB%A6%AC%EA%B7%B81%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95"
    // 농구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20%EB%86%8D%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95
    // 야구 url : https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}%EC%9D%BC%20%EC%95%BC%EA%B5%AC%EA%B2%BD%EA%B8%B0%EC%9D%BC%EC%A0%95

    public LocalDateTime setUrl(int category) {
        //url 초기화
        url = "https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=bjA5&qvt=0&query={yy}%EB%85%84%20{mm}%EC%9B%94%20{dd}";
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String eventDate = nextMonth.format(formatter);

        url = url.replace("{dd}",eventDate.substring(6,8));
        url = url.replace("{mm}",eventDate.substring(4,6));
        url = url.replace("{yy}",eventDate.substring(0,4));
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


        return nextMonth;
    }

    private ArrayList<String []> crawlingSoccerAndBasketball() throws IOException {
        Connection conn = Jsoup.connect(url);

        ArrayList<String []> output = new ArrayList<>();

        Document document = conn.get();
        Elements tr = document.select("tbody._scroll_content tr");

        for(Element e : tr){
            String [] temp = new String[2];
            String name = e.select("td.l_team a").attr("title")+" vs "+e.select("td.r_team a").attr("title");

            String place = e.select("td.place").attr("title") + " 경기장";

            temp[0] = name;
            temp[1] = place;
            output.add(temp);

        }
        return output;
    }

    private ArrayList<String []> crawlingBaseBall() throws IOException {
        Connection conn = Jsoup.connect(url);

        ArrayList<String []> output = new ArrayList<>();
        Document document = conn.get();
        Elements tr = document.select("tbody._scroll_content tr");


        for(Element e : tr){
            String [] temp = new String[2];

            String name = e.select("td.l_team a").attr("title")+" vs "+e.select("td.r_team a").attr("title");

            String place = e.select("td.place a").attr("title") + " 경기장";

            temp[0] = name;
            temp[1] = place;
            output.add(temp);
        }
        return output;
    }

    public ArrayList<String []> crawl(int category) throws IOException {
        ArrayList<String []> output = null;
        if(category==1){
            output = crawlingBaseBall();
        }
        else{
            output = crawlingSoccerAndBasketball();
        }
        return output;
    }

    public void saveEvent(int category) {
        LocalDateTime date = setUrl(category);
        ArrayList<String []> output = null;
        try {
            output = crawl(category);
        } catch (IOException e) {
            e.printStackTrace(); //TODO 오류처리 해야함
        }

        for(String [] s : output){
            Event event = new Event();

            event.setLocation(s[1]);
            event.setName(s[0]);
            event.setDate(date);
            event.setCategoryId(category);

            eventRepository.save(event);

            Board board = new Board();
            board.setEvent(event);
            board.setIsBlind(false);
            boardRepository.save(board);
        }
    }
}
