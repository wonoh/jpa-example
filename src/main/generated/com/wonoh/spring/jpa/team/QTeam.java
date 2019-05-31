package com.wonoh.spring.jpa.team;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = 1077428006L;

    public static final QTeam team = new QTeam("team");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.wonoh.spring.jpa.member.Member, com.wonoh.spring.jpa.member.QMember> members = this.<com.wonoh.spring.jpa.member.Member, com.wonoh.spring.jpa.member.QMember>createList("members", com.wonoh.spring.jpa.member.Member.class, com.wonoh.spring.jpa.member.QMember.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata metadata) {
        super(Team.class, metadata);
    }

}

