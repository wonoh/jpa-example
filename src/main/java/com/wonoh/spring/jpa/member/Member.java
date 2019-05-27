package com.wonoh.spring.jpa.member;

import com.wonoh.spring.jpa.team.Team;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Weight weight;

    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    private Member() {};

    public Member(String name, int age, String email, Team team, Gender gender, Weight weight) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.team = team;
        this.gender = gender;
        this.weight = weight;

        if (!team.getMembers().contains(this)) {
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
    public Gender getGender() {
        return gender;
    }

    public Weight getWeight() {
        return weight;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
