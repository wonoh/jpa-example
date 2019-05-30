package com.wonoh.spring.jpa.member;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wonoh.spring.jpa.fight.Fight;
import com.wonoh.spring.jpa.team.Team;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
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

    @JsonBackReference
    @OneToMany(mappedBy = "member")
    private List<Fight> fights = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    @Builder
    public Member(String name, int age, String email, Team team, Gender gender, Weight weight) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.team = team;
        this.gender = gender;
        this.weight = weight;
        this.createdAt = LocalDateTime.now();

        if (!team.getMembers().contains(this)) {
            team.getMembers().add(this);
        }

    }

}
