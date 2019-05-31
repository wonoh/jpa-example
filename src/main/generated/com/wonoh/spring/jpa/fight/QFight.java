package com.wonoh.spring.jpa.fight;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFight is a Querydsl query type for Fight
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFight extends EntityPathBase<Fight> {

    private static final long serialVersionUID = 1718129226L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFight fight = new QFight("fight");

    public final ListPath<com.wonoh.spring.jpa.comment.Comment, com.wonoh.spring.jpa.comment.QComment> comments = this.<com.wonoh.spring.jpa.comment.Comment, com.wonoh.spring.jpa.comment.QComment>createList("comments", com.wonoh.spring.jpa.comment.Comment.class, com.wonoh.spring.jpa.comment.QComment.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> fightDate = createDateTime("fightDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final com.wonoh.spring.jpa.member.QMember member;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath versus = createString("versus");

    public QFight(String variable) {
        this(Fight.class, forVariable(variable), INITS);
    }

    public QFight(Path<? extends Fight> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFight(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFight(PathMetadata metadata, PathInits inits) {
        this(Fight.class, metadata, inits);
    }

    public QFight(Class<? extends Fight> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.wonoh.spring.jpa.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

