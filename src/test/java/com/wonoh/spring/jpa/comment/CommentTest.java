package com.wonoh.spring.jpa.comment;

import com.wonoh.spring.jpa.common.BaseTest;
import com.wonoh.spring.jpa.fight.Fight;
import com.wonoh.spring.jpa.member.Member;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class CommentTest extends BaseTest {

    private Member member;


    @Before
    public void createMember() throws Exception{
        super.createMember();
        member = super.member;
    }

    @Test
    public void 댓글달기_성공() {

        Fight fight = makeFight();

        Comment comment = Comment.builder()
                .text("댓글")
                .member(member)
                .fight(fight)
                .build();

        Comment savedComment = commentRepository.save(comment);

        assertThat(savedComment.getText(),is(comment.getText()));
        assertThat(savedComment.getId(),is(comment.getId()));

    }

    @Test
    public void 댓글달고_파이트에서댓글조회(){

        Fight fight = makeFight();

        List<Comment> comments = new ArrayList<>();

        Comment comment = Comment.builder()
                .text("댓글1")
                .member(member)
                .fight(fight)
                .build();
        Comment comment1 = Comment.builder()
                .text("댓글2")
                .member(member)
                .fight(fight)
                .build();
        Comment comment2 = Comment.builder()
                .text("댓글3")
                .member(member)
                .fight(fight)
                .build();

        comments.add(comment);
        comments.add(comment1);
        comments.add(comment2);

        commentRepository.saveAll(comments);


        Fight fightAfterSaveComment = fightRepository.findById(fight.getId()).get();

        List<Comment> commentInFight = fightAfterSaveComment.getComments();

        assertThat(commentInFight.get(0).getId(),is(comment.getId()));
        assertThat(commentInFight.get(1).getId(),is(comment1.getId()));
        assertThat(commentInFight.get(2).getId(),is(comment2.getId()));

    }


    private Fight makeFight(){

        Fight fight = Fight.builder()
                .fightDate(LocalDateTime.of(2019,8,25,3,30))
                .location("강남")
                .member(member)
                .price(30000)
                .versus("김진짜")
                .build();

        return fightRepository.save(fight);
    }

}