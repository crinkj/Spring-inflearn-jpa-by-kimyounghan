package jpabook.jpashop.springdatajpa.repository;


import jpabook.jpashop.springdatajpa.entity.DataJpaMember;
import jpabook.jpashop.springdatajpa.entity.DataJpaTeam;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataJpaMemberRepository extends JpaRepository<DataJpaMember,Long> {
    List<DataJpaMember> findByUsername(String userName);

    @Modifying(clearAutomatically = true) // clearAutomatically를 반영함으로써 flush 되고 clear된다
    @Query("update DataJpaMember m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from DataJpaMember m left join fetch m.team")
    List<DataJpaMember> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<DataJpaMember> findAll();


    @Query("select m from DataJpaMember  m where  m.team = :team")
    List<DataJpaMember> findDataJpaMembersByTeam(@Param("team") DataJpaTeam team);

    /**
     *  컬렉션을 조건절에 사용할때
     */
    @Query("select m from DataJpaMember m where m.team in :teams")
    List<DataJpaMember> findMembersByTeamList(@Param("teams") List<DataJpaTeam> teams);
}
