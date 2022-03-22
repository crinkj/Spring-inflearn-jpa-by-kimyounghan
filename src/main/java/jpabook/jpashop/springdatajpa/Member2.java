package jpabook.jpashop.springdatajpa;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터 없는 기본생성자 만들어준다 PROTECTED LEVEL
@ToString(of = {"id", "username","age"})
public class Member2 {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String username;
    private int age;

    // cascade = cascadeType.all 을 꼭붙혀줘서 영속성 전이를 해줘야한다
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="team_id")
    private Team team;

    public Member2(String username, int age, Team team){
        this.username = username;
        this.age = age;
        this.team = team;
    }

    public Member2(String username, int age) {
        this.username = username;
        this.age = age;
    }

    // 연관관계를 변경할수있는 함수를 만들어줘야한다.
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this); // 객체이기때문에 연관관계에있는 테이블 값도 바꿔줘야한다
    }
}
