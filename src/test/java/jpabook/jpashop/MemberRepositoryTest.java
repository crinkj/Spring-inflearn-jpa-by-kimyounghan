package jpabook.jpashop;

import jpabook.jpashop.springdatajpa.Member;
import jpabook.jpashop.springdatajpa.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    // 멤버 객체를 반환하지않은 이유: CQS(command query separation) 커맨드와 커리를 분리하기 위해
    // ** 개발 전반에 기본개념 **
    //   - 메소드를 호출햇을때, 내부에서 변경(사이드 이펙트가)일어나는 메서드인지 아닌지 명확히 분리해야한다
    //   - 권장하는 방법:  insert는 id만 반환하고,
    //                  update는 아무것도 반환하지않고
    //                  select는 내부의 변경이 없는 메소드로 설계하면 좋다
    // tdd 치면 template 나오게 하는방법(preference -> live templates -> custom 템플릿 만듬)
    // Entity를 통한 모든 데이터 변경은 항상 트랜젝션 안에 이루어져야한다.
    //          @transactional 없이 데이터 변경을 할경우 에러가난다
    @Test
    @Transactional  // 테스트에서는 진행한후 Rollback 시킨다
    // @Rollback(false) // 테스트에서도 Rollback 원하지않는경우 false 지정
    public void bulkTest() throws Exception{
        memberRepository.save(new Member("member1",10));
        memberRepository.save(new Member("member2",20));
        memberRepository.save(new Member("member3",30));
        memberRepository.save(new Member("member4",40));

        memberRepository.bulkAgePlus(20);

        List<Member> result = memberRepository.findByUsername("member5");
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5);


    }
    
}