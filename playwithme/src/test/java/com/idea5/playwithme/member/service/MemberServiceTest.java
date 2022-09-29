//package com.idea5.playwithme.member.service;
//
//import com.idea5.playwithme.member.domain.Member;
//import com.idea5.playwithme.member.dto.MemberRecruitDto;
//import com.idea5.playwithme.member.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class MemberServiceTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    MemberService memberService;
//
//    @Test
//    public void test(){
//        List<MemberRecruitDto> recruitMember = memberService.findRecruitMember(12L, 1L);
//        for (MemberRecruitDto member : recruitMember) {
//            System.out.println("member.getNickname() = " + member.getNickname());
//            System.out.println("member.getId() = " + member.getId());
//        }
//    }
//
//
//
//}