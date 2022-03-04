package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // 멤버 객체를 반환하지않은 이유: CQS(command query separation) 커맨드와 커리를 분리하기 위해
    // ** 개발 전반에 기본개념 **
    //   - 메소드를 호출햇을때, 내부에서 변경(사이드 이펙트가)일어나는 메서드인지 아닌지 명확히 분리해야한다
    //   - 권장하는 방법:  insert는 id만 반환하고,
    //                  update는 아무것도 반환하지않고
    //                  select는 내부의 변경이 없는 메소드로 설계하면 좋다
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
