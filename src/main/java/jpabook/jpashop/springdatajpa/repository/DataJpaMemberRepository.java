package jpabook.jpashop.springdatajpa.repository;


import jpabook.jpashop.springdatajpa.entity.DataJpaMember;
import jpabook.jpashop.springdatajpa.entity.DataJpaTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * 스프링 데이터 JPA 페이징과 정렬
     *  반환타입을 org.springframework.data.domain.Page -> totalCount 쿼리랑 같이나간다.
     *  반환타입을 org.springframework.data.domaain.Slice -> totalCcount 쿼리는 안나간다.
     *         - slice를 사용할경우 원하는데이터값 +1 되서 반환된다.
     */
    @Query("select m from DataJpaMember m where m.age > :age")
    Page<DataJpaMember> findByAgeForPaging(@Param("age") int age, Pageable pageable);

    /** Count Query를 분리할 수 있다. 복잡한 쿼리경우 count쿼리를 날릴때도 쓸데없는 join이 걸려서 성능이 느려진다.
     *           그럴시에는 밑에 처럼 countQuery를 분리하는게 좋다
     *  @Query(value = "select m from DataJpaMember m where m.age > :age",
     *                  countQuery = "select count(m) from DataJpaMember m");
     *  Page<DataJpaMember> findByAgeForPaging(@Param("age") int age, Pageable pageable);
     */
}
