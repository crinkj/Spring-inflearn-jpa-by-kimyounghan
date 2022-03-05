package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    // tdd 치면 template 나오게 하는방법(preference -> live templates -> custom 템플릿 만듬)
    // Entity를 통한 모든 데이터 변경은 항상 트랜젝션 안에 이루어져야한다.
    //          @transactional 없이 데이터 변경을 할경우 에러가난다
    @Test
    @Transactional  // 테스트에서는 진행한후 Rollback 시킨다
    @Rollback(false) // 테스트에서도 Rollback 원하지않는경우 false 지정
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // JPA의 중요한 개념인 persist context(영속성)과 관련이 있다.
        // 영속성 컨텍스트에 담겨서 같은 동일성을 보장한다.
       assertThat(findMember).isEqualTo(member);


    }
    
}