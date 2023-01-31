package solo.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class BoardApplicationTests {
    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        Member member3 = new Member("member3");
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        List<Member> members = em.createQuery("select m from Member m")
                .getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);

        }
    }
}
