package jpabook.jpashop.queryDSL.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// JPA 는 기본생성자가 필요
// JPA 기본스펙에서는 protected-level 까지 기본생성자를 지원해준다
@Entity
@Getter @Setter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Table(name = "QTeam")
public class QueryDSLTeamEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy= "team")
    private List<QueryDSLMemberEntity> members = new ArrayList<>();

    public QueryDSLTeamEntity(String name){
        this.name = name;
    }
}
