package com.wonoh.spring.jpa.member;

import com.wonoh.spring.jpa.common.BaseTest;
import com.wonoh.spring.jpa.common.ErrorCode;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class MemberTest extends BaseTest {


    @Test
    public void 멤버등록() throws Exception {

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
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("age").value(age))
                .andExpect(jsonPath("team").exists())
                .andExpect(jsonPath("gender").value(Gender.MAIL.toString()))
                .andExpect(jsonPath("weight").value(Weight.MIDDLE.toString()));

    }

    @Test
    public void 멤버등록_빈값_유효성검증실패() throws Exception {


        int age = 27;
        String name = "지원오";
        String email = "wldnjsdh4412@naver.com";
        String teamName = "";
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
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("code").value(ErrorCode.INPUT_VALUE_INVALID.getCode()))
                .andExpect(jsonPath("status").value(400))
                .andExpect(jsonPath("errors").exists());

    }

    @Test
    public void 멤버등록_이메일양식_유효성검증실패() throws Exception {

        int age = 27;
        String name = "지원오";
        String email = "wldnjsdh4412naver.com";
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
        )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message").exists())
                .andExpect(jsonPath("code").value(ErrorCode.INPUT_VALUE_INVALID.getCode()))
                .andExpect(jsonPath("status").value(400))
                .andExpect(jsonPath("errors").exists());

    }
    @Test
    public void querydsl_기본기능() throws Exception {
        createMember();

        List<Member> members = memberRepositorySupport.findAll();

        assertThat(members.size(),is(1));
        assertThat(members.get(0).getName(),is("지원오"));

    }


}