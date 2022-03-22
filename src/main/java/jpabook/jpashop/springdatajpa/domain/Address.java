package jpabook.jpashop.springdatajpa.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

// JPA 내장타입인걸 표시하기위해 달아주는 어노테이션
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
