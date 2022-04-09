package queryDSL.entity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username"}) // ToString에는 연관관계 필드 참조 안하는게 좋다. 순환참조 방지
@Table(name = "QMember")
public class QueryDSLMemberEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    private QueryDSLTeamEntity team;

    // 이름 기본생성자
    public QueryDSLMemberEntity(String username){
        this(username, 0);
    }

    // 이름/나이 기본생성자
    public QueryDSLMemberEntity(String username, int age){
        this(username, age, null);
    }

    // 이름/나이/팀 기본생성자
    public QueryDSLMemberEntity(String username, int age, QueryDSLTeamEntity team){
        this.username = username;
        this.age = age;
        if(team !=null){
            changeTeam(team);
        }
    }

    public void changeTeam(QueryDSLTeamEntity team){
        this.team = team;
        team.getMembers().add(this);
    }
}
