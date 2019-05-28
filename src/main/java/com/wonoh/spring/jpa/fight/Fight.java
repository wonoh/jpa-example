package com.wonoh.spring.jpa.fight;


import com.wonoh.spring.jpa.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fight")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "versus")
    private String versus;

    @Builder
    public Fight(LocalDateTime fightDate,String location,int price,Member member,String versus){

        this.fightDate = fightDate;
        this.location = location;
        this.price = price;
        this.member = member;
        this.versus = versus;
    }

}
