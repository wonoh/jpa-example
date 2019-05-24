package com.wonoh.spring.jpa.member;

import com.wonoh.spring.jpa.team.Team;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "age",nullable = false)
    private int age;


    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    private Member(){};

    public Member(String name,int age){
        this.name = name;
        this.age = age;
    }

    public void setTeam(Team team){

        this.team = team;

        // 무한루프에 빠지지 않기 위함
        if(!team.getMembers().contains(this)){

            team.getMembers().add(this);
        }
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Team getTeam() {
        return team;
    }
}
