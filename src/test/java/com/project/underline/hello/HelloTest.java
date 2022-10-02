package com.project.underline.hello;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.project.underline.hello.QHello.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class HelloTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void helloTest() {
        Hello test = new Hello();
        em.persist(test);

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Hello findHello = queryFactory.selectFrom(hello)
                .fetchOne();

        Assertions.assertThat(findHello).isEqualTo(test);
        Assertions.assertThat(test.getId()).isEqualTo(test.getId());
    }

}