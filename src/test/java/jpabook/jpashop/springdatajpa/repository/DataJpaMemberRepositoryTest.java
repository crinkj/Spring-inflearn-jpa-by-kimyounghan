package jpabook.jpashop.springdatajpa.repository;

import jpabook.jpashop.springdatajpa.entity.DataJpaMember;
import jpabook.jpashop.springdatajpa.entity.DataJpaTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        for (int i = 0; i < 10; i++) {
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

        assertThat(10).isEqualTo(dataJpaMemberRepository.findAll().size());
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
}