package jpabook.jpashop.springdatajpa.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString(of = {"id", "name"})
public class DataJpaTeam {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team",  cascade = CascadeType.ALL)
    private List<DataJpaMember> members = new ArrayList<>();

    public DataJpaTeam(String name){
        this.name = name;
    }
}
