package com.wonoh.spring.jpa.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonoh.spring.jpa.comment.CommentRepository;
import com.wonoh.spring.jpa.fight.FightRepository;
import com.wonoh.spring.jpa.member.Member;
import com.wonoh.spring.jpa.member.MemberRepository;
import com.wonoh.spring.jpa.member.MemberRepositorySupport;
import com.wonoh.spring.jpa.member.MemberRequestDto;
import com.wonoh.spring.jpa.team.TeamRepository;
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

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("classpath:application-test.properties")
@Transactional
public abstract class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected TeamRepository teamRepository;

    @Autowired
    protected CommentRepository commentRepository;

    @Autowired
    protected FightRepository fightRepository;

    @Autowired
    protected MemberRepositorySupport memberRepositorySupport;

    protected Member member;

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




}
