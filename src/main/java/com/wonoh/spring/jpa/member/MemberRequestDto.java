package com.wonoh.spring.jpa.member;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wonoh.spring.jpa.team.Team;

import javax.validation.constraints.*;


public class MemberRequestDto {


    @Min(value = 0,message = "나이는 0보다 커야합니다.")
    private int age;

    @NotBlank(message = "이름을 작성해주세요.") @NotEmpty(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "이메일을 작성해주세요.") @NotEmpty(message = "이메일을 작성해주세요.")  @Email(message = "메일의 양식을 지켜주세요.")
    private String email;

    @NotBlank(message = "팀이름을 작성해주세요.") @NotEmpty(message = "팀이름을 작성해주세요.")
    private String teamName;

    private MemberRequestDto(){};

    public MemberRequestDto(int age,String name,String email,String teamName){
        this.age = age;
        this.name = name;
        this.email = email;
        this.teamName = teamName;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTeamName() {
        return teamName;
    }

    public Member toEntity(){

        Team team = new Team(teamName);

        return new Member(name,age,email,team);
    }
}
