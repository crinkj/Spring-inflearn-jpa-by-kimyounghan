* Entity 설계시 주의점
이론적으로 Getter,Setter 모두 제공하지 않고, 필요한 경우 별도의 메소드를 제공하는게
가장 시장적이다. 하지만 실무에서는 조회할 일이 많으므로 Getter는 열어두고 개발하는것이 편하다. 
Getter 는 열어두고 개발을 해도 데이터가 변하지않는다
하지마 Setter는 안된다. Setter 같은 경우에는 호출하는 순간
데이터가 변경되므로, 나중에 유지보수 할때 엔티티가 어디서 변경됬는지 추적하기 힘들다.
엔티티에 변경이 필요한경우 Setter대신에 명확하게 비즈니스 메서드를 별도로 제공한후
그 안에서 변경을 해야한다. 

요약
- Getter는 실무에서 오픈하면서 사용하는게 편하다
- Setter는 데이터의 변경지점을 찾기 어려움으로 사용하면안된다.
- 유지보수 문제가 크다(찾기 어려움 -> 생산성 저하)


* 기본값 fetch: @ManyToOne(fetch = FetchType.EAGER)
* FetchType.EAGER(즉시로딩)
* FetchType.LAZY(지연로딩)
- 실무에서는 무조건 FetchTYpe.LAZY 로 세팅을 해야한다.EAGER로 세팅을 하면 연관된 데이터를 각 테이블에서 다 가져온다.
- 만약 EAGER로 세팅인경우 예측이 어렵고 어떤 SQL이 실행될지 추적하기 어렵다. 
- @OneToMany 나 @OneToOne은 default가 FetchType.Lazy이다.
- @ManyToOne은 default가 FetchType.EAGER이므로 @ManyToONe(fetch = FetchType.LAZY) 로 세팅해야한다.

* JPA 테이블,컬럼명 규격
- 하이버네이트 같은경 기본으로 entity에서 카멜케이스가 DB에서 _바로 변환된다 예)memberPoint -> member_point