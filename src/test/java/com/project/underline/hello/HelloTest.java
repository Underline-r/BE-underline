package com.project.underline.hello;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class HelloTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void helloTest() {
        Hello hello = new Hello();
        em.persist(hello);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Hello findHello = queryFactory.selectFrom(QHello.hello)
                .fetchOne();

        Assertions.assertThat(findHello).isEqualTo(hello);
        Assertions.assertThat(hello.getId()).isEqualTo(hello.getId());
    }

}