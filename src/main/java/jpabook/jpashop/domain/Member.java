package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity : 하나의 객체 단위이며 하나의 데이터 테이블 값을 의미한다.
@Entity
@Getter @Setter
public class Member {

    // @Id : entity의 primary key이다.
    // @GeneratedValue : Mysql의 auto increment 같은 기능을 한다.
    //                   마지막 pk값 +1. auto increment 컬럼인것을 Entity Management한테 알려준다.
    // @Column(name= "${name}") 을 명시안하면 작성한 변수값이 컬럼으로 생성이 된다
    @Id @GeneratedValue
    @Column(name ="member_id")
    private Long id;

    private String name;

    // 보통 내장하는 클래스에서 @Embeddable만 쓰던가
    // 내장하려는 클래스에 embedded만쓴다.
    @Embedded
    private Address address;

    // 하나의 멤버가 다중 상품을 구매할 수 있다
    // 일대다의 관계
    // 연관관계중 주인이 아니라는걸 명시
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();




}
