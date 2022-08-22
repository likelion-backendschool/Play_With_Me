package com.idea5.playwithme.event.crawling;

import com.idea5.playwithme.event.service.crawling.ConcertAndMusicalCrawlService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConcertAndMusicalCrawlServiceTest {
    ConcertAndMusicalCrawlService crawlService = new ConcertAndMusicalCrawlService();

    @Test
    public void 크롤링할_주소_입력_테스트_날짜(){
        crawlService.setUrl(5);

        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH,1);

        System.out.println(cal);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String s  = format.format(cal.getTime());

        assertThat(crawlService.getUrl()).contains(s);
    }

    @Test
    public void 크롤링_진행_후_이벤트_이름과_장소가_개수가_맞게_들어왔는지_테스트() throws IOException {
        crawlService.setUrl(6);

        List<String> names = crawlService.crawlEventName();
        List<String> places = crawlService.crawlEventLocation();

        assertThat(names.size()).isNotEqualTo(0);
        assertThat(names.size()).isEqualTo(places.size());
    }
}
