package com.wonoh.spring.jpa.fight;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonoh.spring.jpa.member.Member;
import com.wonoh.spring.jpa.member.MemberRepository;
import com.wonoh.spring.jpa.member.MemberRequestDto;
import com.wonoh.spring.jpa.team.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class FightTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FightRepository fightRepository;

    private Member member;

    @Before
    public void createMember() throws Exception{
        int age = 27;
        String name = "지원오";
        String email = "wldnjsdh4412@naver.com";
        String teamName = "팀매드";
        String gender = "남";
        int weight = 80;

        MemberRequestDto dto = MemberRequestDto.builder()
                .age(age)
                .name(name)
                .email(email)
                .teamName(teamName)
                .gender(gender)
                .weight(weight)
                .build();

        mockMvc.perform(post("/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto))
        );
        member = memberRepository.findAll().get(0);
    }

    @Test
    public void 파이트생성_성공() {


        Fight fight = Fight.builder()
                .fightDate(LocalDateTime.of(2019,8,25,3,30))
                .location("강남")
                .member(member)
                .price(30000)
                .versus("김")
                .build();

        Fight savedFight = fightRepository.save(fight);

        assertThat(savedFight.getLocation(),is(fight.getLocation()));
        assertThat(savedFight.getFightDate(),is(fight.getFightDate()));
        assertThat(savedFight.getId(),is(fight.getId()));

    }

}