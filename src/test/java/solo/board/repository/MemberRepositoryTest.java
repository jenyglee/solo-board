package solo.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.Member;
import solo.board.entity.MemberRole;
import solo.board.entity.Request;
import solo.board.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 멤버_권한변경_요청_승인(){
        Member admin = createAdmin();
        Member member = memberRepository.save(Member.createMember("member1@asd.asd", "1111", "고객", "부천시", "원미로", "232번길"));
        Request request = memberService.createRequest(MemberRole.SELLER, member);
        memberService.approveRequest(admin.getId(), request.getId());
    }


    private Member createAdmin() {
        Member member = memberRepository.save(Member.createMember("admin@asd.asd", "1111", "주인", "부천시", "원미로", "232번길"));
        member.changeAdmin();
        return member;
    }

}