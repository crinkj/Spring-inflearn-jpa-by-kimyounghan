package jpabook.jpashop.springdatajpa.repository;

import jpabook.jpashop.springdatajpa.entity.DataJpaTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 인터페이스를 상속 받을때 사용하는 용도에 따라 다른 JPA인터페이스를 상속받아야한다.
 * - CrudRepository: CRUD 관련 기능
 * - PagingAndSortingRepository: 페이징 및 sorting 관련 기능들 제공
 * - JpaRespository: JPA 관련 특화 기능등( CrudRepository와 PagingAndSortingRepository의 기능들)

 */
public interface DataJpaTeamRepository extends JpaRepository<DataJpaTeam, Long> {

    @Query("select t from DataJpaTeam t where t.id =:id")
    DataJpaTeam findById(@Param("id") long id);

    @Query("select t from DataJpaTeam  t where t.id <:num")
    List<DataJpaTeam> findTeamList(@Param("num") Long num);

}
