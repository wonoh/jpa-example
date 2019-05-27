package com.wonoh.spring.jpa.member;

import com.wonoh.spring.jpa.team.Team;

public class MemberResponseDto {

    private int age;
    private String name;
    private String email;
    private Team team;

    private MemberResponseDto(){};

    public MemberResponseDto(Member member){
        this.age =member.getAge();
        this.name = member.getName();
        this.email = member.getEmail();
        this.team = member.getTeam();

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

    public Team getTeam() {
        return team;
    }
}
