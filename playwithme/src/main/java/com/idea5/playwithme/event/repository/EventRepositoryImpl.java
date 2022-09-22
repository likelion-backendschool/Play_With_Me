package com.idea5.playwithme.event.repository;

import com.idea5.playwithme.event.domain.Event;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.idea5.playwithme.article.domain.QArticle.article;
import static com.idea5.playwithme.event.domain.QEvent.event;

@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Event findTopEventByArticleCount(Integer categoryNo){ // TODO: QueryDSL 사용하지 않고 최대한 JPA 사용하는 방향으로 리팩토링
        return jpaQueryFactory
                .select(event)
                .from(event)
                .innerJoin(article)
                .on(event.id.eq(article.board.id))
                .where(event.categoryId.eq(categoryNo))
                .groupBy(event.id)
                .orderBy(Wildcard.countAsInt.desc())
                .limit(1)
                .fetchOne();
    }

}