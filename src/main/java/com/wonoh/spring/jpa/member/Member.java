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

    @ManyToOne
    private Team team;


}
