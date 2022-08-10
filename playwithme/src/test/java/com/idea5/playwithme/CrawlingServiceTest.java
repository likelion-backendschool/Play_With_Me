package com.idea5.playwithme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CrawlingServiceTest {
    CrawlService crawlService = new CrawlService();

    @Test
    public void 크롤링할_주소_입력_테스트_날짜(){
        crawlService.setDate();

        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH,1);

        System.out.println(cal);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String s  = format.format(cal.getTime());

        assertThat(crawlService.getUrl()).contains(s);
    }

    @Test
    public void 크롤링_진행_후_이벤트_이름과_장소가_개수가_맞게_들어왔는지_테스트() throws IOException {
        crawlService.setDate();

        List<String> names = crawlService.crawlEventName();
        List<String> places = crawlService.crawlEventPlace();

        assertThat(names.size()).isEqualTo(places.size());
    }
}
