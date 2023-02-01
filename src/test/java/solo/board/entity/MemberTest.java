package solo.board.entity;

import com.querydsl.jpa.JPQLQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import solo.board.repository.MemberRepository;
import solo.board.service.MemberService;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void getMember(){
        memberService.createMember("member1", "1234", "멤버1", "부천", "원미로", "123번길");
        memberService.createMember("member2", "1234", "멤버1", "부천", "원미로", "123번길");
        memberService.createMember("member3", "1234", "멤버2", "부천", "원미로", "123번길");
        memberService.createMember("member4", "1234", "멤버2", "부천", "원미로", "123번길");

        List<Member> member1 = memberService.searchMember("member1");
        System.out.println("member1 = " + member1.get(0).getEmail());


    }

}