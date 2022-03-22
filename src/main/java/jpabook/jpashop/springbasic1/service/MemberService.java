package jpabook.jpashop.springbasic1.service;

import jpabook.jpashop.springbasic1.domain.Member;
import jpabook.jpashop.springbasic1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// readonly= true 넣는 경우 데이터 변경이 불가하며 속도가 빨라진다.
// 기본적으로 @transactional은 readonly=false이다
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
         List<Member> findMembers = memberRepository.findByName(member.getName());
         if(!findMembers.isEmpty()){
              throw new IllegalStateException("이미 존재하는 회원입니다.");
         }
    }

    // 회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
