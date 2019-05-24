package com.wonoh.spring.jpa.team;


import com.wonoh.spring.jpa.member.Member;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members;





}
