package jpabook.jpashop.springdatajpa;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터 없는 기본생성자 만들어준다 PROTECTED LEVEL
@ToString(of = {"id", "username","age"})
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne
    @JoinColumn("name=team_id")
    private Team team;

    public Member(String username, int age, Team team){
        this.username = username;
        if(team != null){
            changeTeam(team);
        }
    }

    // 연관관계를 변경할수있는 함수를 만들어줘야한다.
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this); // 객체이기때문에 연관관계에있는 테이블 값도 바꿔줘야한다
    }
}
