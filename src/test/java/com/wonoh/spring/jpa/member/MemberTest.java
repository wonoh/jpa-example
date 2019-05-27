package com.wonoh.spring.jpa.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wonoh.spring.jpa.common.ErrorCode;
import com.wonoh.spring.jpa.team.Team;
import com.wonoh.spring.jpa.team.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void 멤버등록() throws Exception {

        int age = 27;
        String name = "원오";
        String email = "wnjdsdh@naver.com";
        String teamName = "아스날";

        MemberRequestDto dto = new MemberRequestDto(age, name, email, teamName);

        mockMvc.perform(post("/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("age").value(age))
                .andExpect(jsonPath("team").exists());

    }

    @Test
    public void 멤버등록_빈값_유효성검증실패() throws Exception {

        int age = 27;
        String name = "원오";
        String email = " ";
        String teamName = "아스날";

        MemberRequestDto dto = new MemberRequestDto(age, name, email, teamName);

        mockMvc.perform(post("/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto))
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("code").exists())
                .andExpect(jsonPath("status").exists())
                .andExpect(jsonPath("errors").exists());

    }

    @Test
    public void 멤버등록_이메일양식_유효성검증실패() throws Exception {

        int age = 27;
        String name = "원오";
        String email = "wldnjsdh4412naver.com";
        String teamName = "아스날";

        MemberRequestDto dto = new MemberRequestDto(age, name, email, teamName);

        mockMvc.perform(post("/member")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(dto))
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("code").value(ErrorCode.INPUT_VALUE_INVALID.getCode()))
                .andExpect(jsonPath("status").value(400))
                .andExpect(jsonPath("errors").exists());

    }


}