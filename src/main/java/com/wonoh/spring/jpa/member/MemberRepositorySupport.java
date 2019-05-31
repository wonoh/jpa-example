package com.wonoh.spring.jpa.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.wonoh.spring.jpa.member.QMember.member;

@Repository
public class MemberRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public MemberRepositorySupport(JPAQueryFactory queryFactory){
        super(Member.class);
        this.queryFactory = queryFactory;
    }

    public List<Member> findAll(){
        return queryFactory
                .selectFrom(member)
                .fetch();

    }



}
