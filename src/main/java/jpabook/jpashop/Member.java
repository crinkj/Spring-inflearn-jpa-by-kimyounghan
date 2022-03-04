package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity : 하나의 객체 단위이며 하나의 데이터 테이블 값을 의미한다.
@Entity
@Getter @Setter
public class Member {


    // @Id : entity의 primary key이다.
    // @GeneratedValue : Mysql의 auto increment 같은 기능을 한다.
    //                   마지막 pk값 +1. auto increment 컬럼인것을 Entity Management한테 알려준다.
    @Id @GeneratedValue
    private Long id;
    private String username;

}
