package jpabook.jpashop.queryDSL.repository;

import jpabook.jpashop.springbasic1.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryDSLMemberRepository extends JpaRepository<Member,Long> {
}
