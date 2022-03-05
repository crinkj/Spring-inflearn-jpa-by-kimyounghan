package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

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
    @OneToMany(mappedBy = "order")
    private List<Order> orders = new ArrayList<>();




}
