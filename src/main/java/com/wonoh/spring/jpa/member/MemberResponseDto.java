package com.wonoh.spring.jpa.member;

import com.wonoh.spring.jpa.team.Team;

public class MemberResponseDto {

    private int age;
    private String name;
    private String email;
    private Team team;
    private Gender gender;
    private Weight weight;

    private MemberResponseDto(){};

    public MemberResponseDto(Member member){
        this.age =member.getAge();
        this.name = member.getName();
        this.email = member.getEmail();
        this.team = member.getTeam();
        this.gender = member.getGender();
        this.weight = member.getWeight();

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

    public Gender getGender() {
        return gender;
    }

    public Weight getWeight() {
        return weight;
    }

    public Team getTeam() {
        return team;
    }
}
