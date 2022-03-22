package jpabook.jpashop.springdatajpa.domain;

import jpabook.jpashop.springdatajpa.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    // ManyToMany 를 사용할경우 지정한 필드밖에 사용이 불가능하여 ManyToMany는 잘 사용하지 않는다.
    // 실무에서는 중간테이블 연결해서 사용한다
    // joinColumns: 조인해서 사용하는 엔티티의 외래키
    // inverseJoinColumns: 반대 엔테티의 외래키
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
