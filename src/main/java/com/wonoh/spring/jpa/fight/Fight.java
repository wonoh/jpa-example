package com.wonoh.spring.jpa.fight;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.wonoh.spring.jpa.comment.Comment;
import com.wonoh.spring.jpa.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fight")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString(exclude = "member")
public class Fight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fight_date",nullable = false)
    private LocalDateTime fightDate;

    @Column(name = "location",nullable = false)
    private String location;

    @Column(name = "price",nullable = false)
    private int price;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Member member;

    @OneToMany(mappedBy = "fight",fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "versus")
    private String versus;

    @Builder
    public Fight(LocalDateTime fightDate,String location,int price,Member member,String versus){

        this.fightDate = fightDate;
        this.location = location;
        this.price = price;
        this.member = member;
        this.versus = versus;

        if(!member.getFights().contains(this)){
            member.getFights().add(this);
        }
    }

}
