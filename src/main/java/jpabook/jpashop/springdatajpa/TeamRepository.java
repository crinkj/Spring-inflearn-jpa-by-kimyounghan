package jpabook.jpashop.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
