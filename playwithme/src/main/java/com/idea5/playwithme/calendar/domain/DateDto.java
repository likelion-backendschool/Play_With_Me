package com.idea5.playwithme.calendar.domain;

import com.idea5.playwithme.event.domain.Event;
import lombok.Data;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@ToString
@Data
public class DateDto {
    String year = "";
    String month ="";
    String date="";
    String value="";
    String db_startDate="";
    String db_endDate ="";
    Event[] event = new Event[4];

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDb_startDate() {
        return db_startDate;
    }

    public void setDb_startDate(String db_startDate) {
        this.db_startDate = db_startDate;
    }

    public String getDb_endDate() {
        return db_endDate;
    }

    public void setDb_endDate(String db_endDate) {
        this.db_endDate = db_endDate;
    }

    public Event[] getEvent() {
        return event;
    }

    public void setEvent(Event[] event) {
        this.event = event;
    }

    public Map<String,Integer> today_info(DateDto date_data) {
        Map<String, Integer> today_Data = new HashMap<String, Integer>();
        Calendar cal = Calendar.getInstance();

        cal.set(Integer.parseInt(date_data.getYear()), Integer.parseInt(date_data.getMonth()),1);

        int startDay = cal.getMinimum(Calendar.DATE);
        int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int start = cal.get(java.util.Calendar.DAY_OF_WEEK);

        Calendar todayCal = Calendar.getInstance();
        SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat msdf = new SimpleDateFormat("M");

        int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));
        int today_month = Integer.parseInt(msdf.format(todayCal.getTime()));

        int search_year = Integer.parseInt(date_data.getYear());
        int search_month = Integer.parseInt(date_data.getMonth()) +1;

        int today = -1;
        if (today_year == search_year && today_month == search_month) {
            SimpleDateFormat dsdf = new SimpleDateFormat("dd");
            today = Integer.parseInt(dsdf.format(todayCal.getTime()));
        }

        search_month = search_month-1;

        Map<String, Integer> before_after_calendar = before_after_calendar(search_year, search_month);

        System.out.println("search_month :" + search_month);
        // 캘린더 함수 끝

        today_Data.put("start", start);
        today_Data.put("startDay", startDay);
        today_Data.put("endDay", endDay);
        today_Data.put("today",today);
        today_Data.put("search_year", search_year);
        today_Data.put("search_month",search_month);
        today_Data.put("before_year", before_after_calendar.get("before_year"));
        today_Data.put("before_month",before_after_calendar.get("before_month"));
        today_Data.put("after_year",before_after_calendar.get("after_year"));
        today_Data.put("after_month",before_after_calendar.get("after_month"));

        this.db_startDate = String.valueOf(search_year)
                +"-"
                +String.valueOf(search_month+1)
                +"-"
                +String.valueOf(startDay);

        this.db_endDate = String.valueOf(search_year)
                +"-"
                +String.valueOf(search_month+1)
                +"-"
                +String.valueOf(endDay);
        return today_Data;
    }


    private Map <String, Integer> before_after_calendar(int search_year, int search_month) { // 이전 달, 이번 달, 이전 연도, 이번 연도
        Map<String, Integer> before_after_data = new HashMap<String, Integer>();
        int before_year = search_year;
        int before_month = search_month - 1;
        int after_year = search_year;
        int after_month = search_month + 1;

        if (before_month < 0) {
            before_month = 11;
            before_year = search_year - 1;
        }
        if (after_month > 11) {
            after_month = 0;
            after_year = search_year + 1;
        }

        before_after_data.put("before_year", before_year);
        before_after_data.put("before_month", before_month);
        before_after_data.put("after_year", after_year);
        before_after_data.put("after_month", after_month);

        return before_after_data;
    }


    public DateDto(String year, String month, String date, String value, Event[] event) { // 이벤트 스케줄 사용시 사용될 생성자
        if ((month != null && month != "") && (date != null && date != "")) {
            this.year = year;
            this.month = month;
            this.date = date;
            this.value = value;
            this.event = event;

        }
    }

    @Override
    public String toString() {
        return "Date [year=" + year + ", month =" + month + ", date =" + date + ", value =" + value + "]";
    }
}



