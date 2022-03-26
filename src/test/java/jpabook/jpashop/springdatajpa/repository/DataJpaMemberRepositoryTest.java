package jpabook.jpashop.springdatajpa.repository;

import jpabook.jpashop.springdatajpa.entity.DataJpaMember;
import jpabook.jpashop.springdatajpa.entity.DataJpaTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DataJpaMemberRepositoryTest {

    @Autowired
    private DataJpaMemberRepository dataJpaMemberRepository;

    @Autowired
    private DataJpaTeamRepository dataJpaTeamRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void insertDataJpaMembers() {
        dataJpaTeamRepository.save(new DataJpaTeam("TEAM_A"));
        dataJpaTeamRepository.save(new DataJpaTeam("TEAM_B"));
        dataJpaTeamRepository.save(new DataJpaTeam("TEAM_C"));

        for (int i = 0; i < 50; i++) {
            DataJpaMember dataJpaMember = new DataJpaMember();
            dataJpaMember.setUsername("member_" + i);
            dataJpaMember.setAge(i + 10);

            if (i < 3) {
                dataJpaMember.setTeam(dataJpaTeamRepository.getById(1L));
            } else if (i < 7) {
                dataJpaMember.setTeam(dataJpaTeamRepository.getById(2L));
            } else {
                dataJpaMember.setTeam(dataJpaTeamRepository.getById(3L));
            }

            dataJpaMemberRepository.save(dataJpaMember);
        }

    }

    @Test
    @Transactional(readOnly = true)
    public void findMembersByTeam(){

        DataJpaTeam dataJpaTeam = dataJpaTeamRepository.findById(1L);
        assertThat(3).isEqualTo(dataJpaMemberRepository.findDataJpaMembersByTeam(dataJpaTeam).size());
    }

    @Test
    @Transactional(readOnly = true)
    public void findMembersByTeamList(){

        List<DataJpaTeam> dataJpaTeamList= dataJpaTeamRepository.findTeamList(3L);
        List<DataJpaMember> dataJpaMemberList = dataJpaMemberRepository.findMembersByTeamList(dataJpaTeamList);

        assertThat(7).isEqualTo(dataJpaMemberList.size());

        for(DataJpaMember dataJpaMember : dataJpaMemberList){
            System.out.println(dataJpaMember);
        }
    }

    @Test
    public void memberListPaging(){

        // 원하는 데이터 값에따라 페이징 옵션을 정해서 JPA에 보내야한다.
        PageRequest pageRequest = PageRequest.of(5,10, Sort.by(Sort.Direction.ASC, "age"));  // JPA는 페이징 0부터 시작

        Page<DataJpaMember> dataJpaMemberList =  dataJpaMemberRepository.findByAgeForPaging(10,pageRequest);

        for(DataJpaMember member:dataJpaMemberList){
            System.out.println(member);
            System.out.println(dataJpaMemberList.getTotalElements());
        }
    }
}