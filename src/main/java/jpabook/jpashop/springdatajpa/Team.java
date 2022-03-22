package jpabook.jpashop.springdatajpa;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team",  cascade = CascadeType.ALL)
    private List<Member2> members = new ArrayList<>();

    public Team(String name){
        this.name = name;
    }
}
