package com.wonoh.spring.jpa.comment;


import com.wonoh.spring.jpa.fight.Fight;
import com.wonoh.spring.jpa.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "fight")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text",nullable = false,length = 100)
    private String text;

    @ManyToOne
    private Fight fight;

    @ManyToOne
    private Member member;

    @Builder
    public Comment(String text,Fight fight,Member member){
            this.text = text;
            this.fight = fight;
            this.member = member;

            if(!fight.getComments().contains(this)){
                fight.getComments().add(this);
            }

    }



}
