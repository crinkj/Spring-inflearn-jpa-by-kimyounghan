package jpabook.jpashop.springdatajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository2 extends JpaRepository<Member2,Long> {
    List<Member2> findByUsername(String userName);

    @Modifying(clearAutomatically = true) // clearAutomatically를 반영함으로써 flush 되고 clear된다
    @Query("update Member2 m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member2 m left join fetch m.team")
    List<Member2> findMemberFetchJoin();
}
