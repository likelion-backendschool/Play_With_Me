package com.idea5.playwithme.mypage.controller;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.member.domain.Member;
import com.idea5.playwithme.member.dto.MemberInfoDTO;
import com.idea5.playwithme.mypage.domain.Timeline;
import com.idea5.playwithme.mypage.dto.TimelineRequestDto;
import com.idea5.playwithme.member.service.MemberService;
import com.idea5.playwithme.mypage.service.TimelineService;
import com.idea5.playwithme.together.domain.Together;
import com.idea5.playwithme.together.service.TogetherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage/timeline")
public class TimelineController {
    private final MemberService memberService;
    private final TimelineService timelineService;
    private final TogetherService togetherService;

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("")
    public String showTimeline(Model model, Principal principal) {
        // principal null 체크
        if (principal == null) {
            log.info("You are Logged out. Login or Signin Please!");
            return "redirect:/";
        }

        // 현재 로그인한 회원 리턴
        Member member = memberService.findMember(principal.getName());

        // 메뉴 바 정보
        MemberInfoDTO memberInfo = MemberInfoDTO.builder().nickname(member.getNickname()).gender(member.getGender()).build();

        // 해당 회원의 Timeline 리스트
        List<Timeline> timelines = member.getTimelineList();

        // timelines 리스트 이벤트 날짜 기준 내림차순 정렬
        Collections.sort(timelines, new Comparator<Timeline>() {

            @Override
            public int compare(Timeline o1, Timeline o2) {
                if(o1.getEvent().getDate().isAfter(o2.getEvent().getDate())){
                    return -1;
                } else if(o1.getEvent().getDate().isBefore(o2.getEvent().getDate())){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // 위 리스트에서 이벤트들만 추출
        List<Event> events = timelines.stream().map(Timeline::getEvent).collect(Collectors.toList());

        // 리스트에서 중복 이벤트 제거
        events = events.stream().filter(distinctByKey(Event::getName)).collect(Collectors.toList());

        // 현재 날짜 이전 이벤트들만 리스트에 추가
        List<Event> eventsBeforeNow = new ArrayList<>();
        for(int i=0; i< events.size();i++) {
            if (LocalDateTime.now().isAfter(events.get(i).getDate())!=false) {
                eventsBeforeNow.add(events.get(i));
            }
        }

        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("events", eventsBeforeNow);
        model.addAttribute("timelines", timelines);
        model.addAttribute("requestDto", new TimelineRequestDto());

        return "timeline";
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    // 타임라인 메모 작성
    @PostMapping("/memo/{timeline_id}")
    public String saveMemo(Principal principal, @PathVariable("timeline_id") Long id, @Valid TimelineRequestDto timelineRequestDto) {
        // 로그인 회원 리턴
        Member member = memberService.findMember(principal.getName());

        // Together id == Timeline id
        // Together id로 together 리턴
        long togetherId= id;
        Together together = togetherService.findById(togetherId);

        Event event = together.getArticle().getBoard().getEvent();

        timelineService.saveMemo(id, member, together, timelineRequestDto, event);

        return "redirect:/mypage/timeline";
    }

    // 타임라인 메모 수정
    @PostMapping("/memo/modify/{timeline_id}")
    public String modifyMemo(@PathVariable("timeline_id") Long id, TimelineRequestDto timelineRequestDto) {
        timelineService.updateMemo(id, timelineRequestDto);
        return "redirect:/mypage/timeline";
    }

    // 타임라인 메모 삭제
    @GetMapping("/memo/delete/{timeline_id}")
    public String deleteMemo(@PathVariable("timeline_id") Long id) {
        timelineService.deleteMemo(id);
        return "redirect:/mypage/timeline";
    }

}