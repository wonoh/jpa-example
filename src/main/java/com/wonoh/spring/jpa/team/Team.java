package com.wonoh.spring.jpa.team;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wonoh.spring.jpa.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
//@ToString(exclude = "members") 는 추가해도 서로 계속 참조하여 무한루프.. 왜안되지?
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @JsonIgnore  // team 에서 members 를 계속 참조하는 무한루프 방지
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    private Team(){};

    public Team(String name){
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Member> getMembers() {
        return members;
    }


}
