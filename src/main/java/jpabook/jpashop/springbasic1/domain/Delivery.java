package jpabook.jpashop.springbasic1.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy ="delivery")
    private Order order;

    @Embedded
    private Address address;

    // enum타입일때  @Enumerated를 달아줘야한다
    // @Enumerated를 안달아주면 default로 EnumType.Ordinal로 들어간다
    //          EnumType.Ordinal로 들어가면 1,2,3 숫자로 들어가기 때문에
    //          기존 상태들이 밀려나서 큰일난다.
    // @Enumerated(EnumType.STRING) 으로 명시를 해야한다.
    //          스트링 값으로 상태가 변해서 상태가 추가되도 문제가없다.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP
}
