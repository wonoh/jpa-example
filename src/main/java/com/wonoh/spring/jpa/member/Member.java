package com.wonoh.spring.jpa.member;

import com.wonoh.spring.jpa.team.Team;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "email",nullable = false)
    private String email;


    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    private Member(){};

    public Member(String name,int age,String email,Team team){
        this.name = name;
        this.age = age;
        this.email = email;
        this.team = team;

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

    public String getEmail() {
        return email;
    }
}
