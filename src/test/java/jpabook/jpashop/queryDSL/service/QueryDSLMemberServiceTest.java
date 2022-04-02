package jpabook.jpashop.queryDSL.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.jpashop.springbasic1.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class QueryDSLMemberServiceTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    @Test
    public void testQueryDsl(){
        // Q-TYPE 활용
    }
}