package jpabook.jpashop.springbasic1.repository;

import jpabook.jpashop.springbasic1.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// 스프링이 repository를 스프링 빈으로 등록한후 사용하게 해준다
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 영속성 context에 멤버 객체를 보관한다.
    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    // em.createQuery("JPQL",반환타입)
    //  SQL은 테이블을 대상으로 조회하는데
    //  JPQL경우 엔티티를 대상으로 조회한다
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member  m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
