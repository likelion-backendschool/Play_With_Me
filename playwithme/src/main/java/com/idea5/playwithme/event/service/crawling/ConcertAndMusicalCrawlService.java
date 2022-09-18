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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConcertAndMusicalCrawlService implements CrawlService{

    @Getter
    private String url = "http://ticket.interpark.com/tiki/special/TPCalendar.asp?ImgYn=Y&Ca=&KindOfGoods={category}&KindOfFlag=&Type=A&PlayDate={eventDate}";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private BoardRepository boardRepository;

    private final String musicalId = "01011";
    private final String concertId = "01003";

    public LocalDateTime setUrl(int category){ // 매일 날짜 바꾸기 위해서
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        String eventDate = nextMonth.format(formatter);
        url = url.replace("{eventDate}",eventDate);

        switch (category){
            case 4: // 뮤지컬
                url = url.replace("{category}",musicalId);
                break;

            case 5: // 콘서트
                url = url.replace("{category}",concertId);
                break;
        }
        return nextMonth;
    }

    public List<String> crawlEventName() throws IOException {

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

    public List<String> crawlEventLocation() throws IOException {
//        Connection conn = Jsoup.connect("https://ticket.interpark.com/tiki/special/TPCalendar.asp?ImgYn=Y&Ca=&KindOfGoods=01009&KindOfFlag=P&PlayDate=20220805");

        Connection conn = Jsoup.connect(url);

        Elements place = null;
        List<String> locations = new ArrayList<>();


        Document document = conn.get();
        Elements table = document.select("table[width=726]");
        Elements tbody = table.select("tbody"); // tbody 선택

        place = tbody.select("td[width=120] a");
        for(Element e: place){ // 찾은 Element에 대해서 List에 저장
            locations.add(e.text());
        }

        return locations;
    }

    public void saveEvent(int category) { // controller에서 saveEvent를 통해 크롤링 요청
        LocalDateTime date = setUrl(category);
        List<String> names= null;
        List<String> locations = null;

        try{
            names = crawlEventName();
            locations = crawlEventLocation();
        }
        catch (Exception e){
            e.printStackTrace(); //TODO 오류 처리 생각
        }

        for(int i=0;i<names.size();i++){
            Event event = new Event();
            event.setDate(date);
            event.setCategoryId(category);
            event.setName(names.get(i));
            event.setLocation(locations.get(i));
            eventRepository.save(event);

            Board board = new Board();
            board.setEvent(event);
            board.setIsBlind(false);
            boardRepository.save(board);
        }
    }
}