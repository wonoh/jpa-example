package com.wonoh.spring.jpa.member;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wonoh.spring.jpa.team.Team;

import javax.validation.constraints.*;


public class MemberRequestDto {


    @Min(0)
    private int age;

    @NotBlank @NotEmpty
    private String name;

    @NotBlank @NotEmpty  @Email
    private String email;

    @NotBlank @NotEmpty
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
