package jpabook.jpashop.springbasic1.domain.item;

import jpabook.jpashop.springbasic1.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 상속관계 매핑인경우 상속관계 전략을 지정해야한다. 부모쪽에서 지정해야한다
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// 구분자(Book, Album, Movie) 구분하게 도와주는 어노테이션
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
