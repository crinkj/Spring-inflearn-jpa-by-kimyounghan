package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
// @Table(name="${name}")을 명시안하면 그대로 클래스 이름으로 테이블이 생성된다
@Table(name="Orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    // Mapping을 무엇으로 할껀지 정의하는 어노테이션
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // 자바 8에서는 어노테이션 매핑을안해도 Hibernate가 알아서 LocalDateTime은 매핑해준다.
    private LocalDateTime orderDate;

    private OrderStatus status; // 주문상태 [ORDER, CANCEL]

}
