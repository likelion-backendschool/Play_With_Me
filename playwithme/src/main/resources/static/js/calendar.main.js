(function($) {

	"use strict";

	// Setup the calendar with the current date
$(document).ready(function(){
    var date = new Date();
    var today = date.getDate();
    // Set click handlers for DOM elements
    $(".right-button").click({date: date}, next_year);
    $(".left-button").click({date: date}, prev_year);
    $(".month").click({date: date}, month_click);
    $("#add-button").click({date: date}, new_event);
    // Set current month as active
    $(".months-row").children().eq(date.getMonth()).addClass("active-month");
    init_calendar(date);
    var events = check_events(today, date.getMonth()+1, date.getFullYear());
    show_events(events, months[date.getMonth()], today);
});

// Initialize the calendar by appending the HTML dates
function init_calendar(date) {
    $(".tbody").empty();
    $(".events-container").empty();
    var calendar_days = $(".tbody");
    var month = date.getMonth();
    var year = date.getFullYear();
    var day_count = days_in_month(month, year);
    var row = $("<tr class='table-row'></tr>");
    var today = date.getDate();
    // Set date to 1 to find the first day of the month
    date.setDate(1);
    var first_day = date.getDay();
    // 35+firstDay is the number of date elements to be added to the dates table
    // 35 is from (7 days in a week) * (up to 5 rows of dates in a month)
    for(var i=0; i<35+first_day; i++) {
        // Since some of the elements will be blank, 
        // need to calculate actual date from index
        var day = i-first_day+1;
        // If it is a sunday, make a new row
        if(i%7===0) {
            calendar_days.append(row);
            row = $("<tr class='table-row'></tr>");
        }
        // if current index isn't a day in this month, make it blank
        if(i < first_day || day > day_count) {
            var curr_date = $("<td class='table-date nil'>"+"</td>");
            row.append(curr_date);
        }   
        else {
            var curr_date = $("<td class='table-date'>"+day+"</td>");
            var events = check_events(day, month+1, year);
            if(today===day && $(".active-date").length===0) {
                curr_date.addClass("active-date");
                show_events(events, months[month], day);
            }
            // If this date has any events, style it with .event-date
            if(events.length!==0) {
                curr_date.addClass("event-date");
            }
            // Set onClick handler for clicking a date
            curr_date.click({events: events, year: year, month: month+1, day:day}, date_click);
            row.append(curr_date);
        }
    }
    // Append the last row and set the current year
    calendar_days.append(row);
    $(".year").text(year);
}

// Get the number of days in a given month/year
function days_in_month(month, year) {
    var monthStart = new Date(year, month, 1);
    var monthEnd = new Date(year, month + 1, 1);
    return (monthEnd - monthStart) / (1000 * 60 * 60 * 24);    
}

// Event handler for when a date is clicked
function date_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    $(".active-date").removeClass("active-date");
    $(this).addClass("active-date");
    show_events(event.data.events, event.data.month, event.data.day);

      // URL 관련 추가 시작

      // (yyyy-mm-dd) 포맷에 맞게 Date를 String으로 변환
      // 월, 일 2자리 이하시 0 붙여서 나오도록
      var dateString = event.data.year + "-";
      if ((event.data.month) < 10) {
          dateString += "0";
          }
      dateString += (event.data.month) + "-";
      if (event.data.day < 10) {
          dateString += "0";
          }
      dateString += event.data.day;

       let params = (new URL(document.location)).searchParams;
       let categoryName = params.get("category");
       const categoryName_2 = categoryName;
       params.set("category",categoryName_2);
       params.set("date",dateString);

       // 페이지 갱신 없이 URL만 변경하도록
       const state = { 'category':categoryName_2, 'date': dateString }
       const title = ''
       const url = "event?"+ params
       history.pushState(state, title, url)
      // URL 관련 추가 끝

     // ajax로 "/getEvent?category=categoryName_2&date=dateString" 호출해서 List<Event> 받아오기
     $.ajax({
       type:"GET",
       async: false, // 동기 호출
       url:"/getEvent?" + params,
       dataType:"JSON",

       success: function(data){
             console.log("통신성공");
             event_data2.push(data);

             // event-container에 event를 event-card에 담아 전달
              $(".events-container").empty();
              $(".events-container").show(250);
              // 이벤트가 없을 경우
              if(event_data2[0].length===0) {
                 var event_card = $("<div class='event-card'></div>");
                 var event_name = $("<div class='event-name'> 해당 날짜는 이벤트 일정이 없습니다&nbsp;<i class=\"fa fa-spinner\"></i> </div>");
                 $(event_card).css({ "border-left": "10px solid #FF1744" });
                 $(event_card).append(event_name);
                 $(".events-container").append(event_card);
                  }
              // 이벤트가 있는 경우
              else {
                   for(var i=0; i<event_data2[0].length; i++) {
                       var event_card = $("<div class='event-card'></div>");
                       var event_name = $("<div class='event-name'>"+event_data2[0][i].name+"</div>");
                       var event_location = $("<div class='event-location'><i class=\"fa fa-location-arrow\"></i>&nbsp;"+event_data2[0][i].location+"</div>");
                       var event_dateString = $("<div class='event-dateString'><i class=\"fa fa-calendar-o\"></i>&nbsp;"+event_data2[0][i].date.split("T")[0]+"</div>");
                       var event_urlNotice = $("<div class='id event-urlNotice'> 모집 게시판 바로가기 </div>");
                       $(event_card).append(event_name).append(event_location).append(event_dateString).append(event_urlNotice);
                       $(".events-container").append(event_card);
                   }

                   // 모집 게시판 클릭시 해당 BoardId 리턴하도록
                   const event_cards = document.querySelectorAll(".id");
                   event_cards.forEach(el => {
                     el.onclick = (e) => {
                       const nodes = [...e.target.parentElement.parentElement.children]; // event_card의 부모인 events_container 자식들(계층적 단위) 저장
                       //console.log(e.target.parentElement.parentElement, nodes);
                       const event_cards_index = nodes.indexOf(e.target.parentElement); // 해당 event_card가 events_container의 몇 번째 자식인지 index 리턴

                       // ajax로 boardId 받아오도록 event_cards_index를 URL 파라미터에 추가
                       let params = (new URL(document.location)).searchParams;
                        params.set("index",event_cards_index);

                       // ajax로 BoardId 요청
                       $.ajax({
                              type:"GET",
                              async: false,
                              url:"/getBoardId?" + params,
                              dataType:"JSON",

                              success: function(data){
                                    console.log("통신성공");
                                    var board_id= data; // 받아온 데이터 board_id에 저장

                                    // boardId만을 URL 파라미터로 설정하여 해당 이벤트와 일대일 관계인 board 페이지로 이동
                                    let params = (new URL(document.location)).searchParams;
                                    params.delete('date');
                                    params.delete('category');
                                    params.set("id",board_id);

                                    window.location.href = "/event/board?" + params
                              },
                              error:function(){
                                    console.log("통신에러");
                              }
                          }) // ajax 요청 끝
                       }
                   }); // 이벤트 카드 클릭 이벤트 핸들러 끝
              }

             // 객체 비워주기
             event_data2.length=0;
        },
       error:function(){
             console.log("통신에러");
        }
     })
};

// Event handler for when a month is clicked
function month_click(event) {
    $(".events-container").show(250);
    $("#dialog").hide(250);
    var date = event.data.date;
    $(".active-month").removeClass("active-month");
    $(this).addClass("active-month");
    var new_month = $(".month").index(this);
    date.setMonth(new_month);
    init_calendar(date);
}

// Event handler for when the year right-button is clicked
function next_year(event) {
    $("#dialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear()+1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}

// Event handler for when the year left-button is clicked
function prev_year(event) {
    $("#dialog").hide(250);
    var date = event.data.date;
    var new_year = date.getFullYear()-1;
    $("year").html(new_year);
    date.setFullYear(new_year);
    init_calendar(date);
}

// Event handler for clicking the new event button
function new_event(event) {
    // if a date isn't selected then do nothing
    if($(".active-date").length===0)
        return;
    // remove red error input on click
    $("input").click(function(){
        $(this).removeClass("error-input");
    })
    // empty inputs and hide events
    $("#dialog input[type=text]").val('');
    $("#dialog input[type=number]").val('');
    $(".events-container").hide(250);
    $("#dialog").show(250);
    // Event handler for cancel button
    $("#cancel-button").click(function() {
        $("#name").removeClass("error-input");
        $("#count").removeClass("error-input");
        $("#dialog").hide(250);
        $(".events-container").show(250);
    });
    // Event handler for ok button
    $("#ok-button").unbind().click({date: event.data.date}, function() {
        var date = event.data.date;
        var name = $("#name").val().trim();
        var count = parseInt($("#count").val().trim());
        var day = parseInt($(".active-date").html());
        // Basic form validation
        if(name.length === 0) {
            $("#name").addClass("error-input");
        }
        else if(isNaN(count)) {
            $("#count").addClass("error-input");
        }
        else {
            $("#dialog").hide(250);
            console.log("new event");
            new_event_json(name, count, date, day);
            date.setDate(day);
            init_calendar(date);
        }
    });
}

// Adds a json event to event_data
function new_event_json(name, count, date, day) {
    var event = {
        "occasion": name,
        "invited_count": count,
        "year": date.getFullYear(),
        "month": date.getMonth()+1,
        "day": day
    };
    event_data["events"].push(event);
}

// 클릭하기 전 렌더링되는 이벤트 폼
// Display all events of the selected date in card views
function show_events(events, month, day) {
    // Clear the dates container
    $(".events-container").empty();
    $(".events-container").show(250);
    //console.log(event_data["events"]);
    // If there are no events for this date, notify the user
    if(events.length===0) {
        var event_card = $("<div class='event-card'></div>");
        var event_name = $("<div class='event-name'>동행 모집 원하는 날짜를 클릭해보세요! </div>");
        $(event_card).css({ "border-left": "10px solid #FF1744" });
        $(event_card).append(event_name);
        $(".events-container").append(event_card);
    }
    else {
        // Go through and add each event as a card to the events container
        for(var i=0; i<events.length; i++) {
            var event_card = $("<div class='event-card'></div>");
            var event_name = $("<div class='event-name'>"+events[i]["occasion"]+":</div>");
            var event_count = $("<div class='event-count'>"+events[i]["invited_count"]+" Invited</div>");
            if(events[i]["cancelled"]===true) {
                $(event_card).css({
                    "border-left": "10px solid #FF1744"
                });
                event_count = $("<div class='event-cancelled'>Cancelled</div>");
            }
            $(event_card).append(event_name).append(event_count);
            $(".events-container").append(event_card);
        }
    }
}

// Checks if a specific date has any events
function check_events(day, month, year) {
    var events = [];
    for(var i=0; i<event_data["events"].length; i++) {
        var event = event_data["events"][i];
        if(event["day"]===day &&
            event["month"]===month &&
            event["year"]===year) {
                events.push(event);
            }
    }
    return events;
}
// ajax로 가져온 이벤트 리스트 담을 배열
var event_data2=[];

// 오픈소스에 담겨있던 샘플 데이터
// Given data for events in JSON format
var event_data = {
    "events": [
    /*
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
        {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10,
        "cancelled": true
    },
    {
        "occasion": " Repeated Test Event ",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 10
    },
    {
        "occasion": " Test Event",
        "invited_count": 120,
        "year": 2020,
        "month": 5,
        "day": 11
    }
    */
    ]
};


const months = [
    1,
    2,
    3,
    4,
    5,
    6,
    7,
    8,
    9,
    10,
    11,
    12
];

})(jQuery);
