package com.wonoh.spring.jpa.fight;

import com.wonoh.spring.jpa.common.BaseTest;
import com.wonoh.spring.jpa.member.Member;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;



public class FightTest extends BaseTest {


    private Member member;

    @Before
    public void createMember() throws Exception {
        super.createMember();
        member = super.member;
    }

    @Test
    public void 파이트생성_성공() {


        Fight fight = Fight.builder()
                .fightDate(LocalDateTime.of(2019,8,25,3,30))
                .location("강남")
                .member(member)
                .price(30000)
                .versus("김진짜")
                .build();

        Fight savedFight = fightRepository.save(fight);

        assertThat(savedFight.getLocation(),is(fight.getLocation()));
        assertThat(savedFight.getFightDate(),is(fight.getFightDate()));
        assertThat(savedFight.getId(),is(fight.getId()));

    }

}