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
import solo.board.service.RequestService;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    RequestService requestService;

    @Test
    public void 멤버_생성(){
        //고객
        Member customer = Member.createMember("customer@aaa.aaa", "1234", "이재원", "부천시", "원미로", "232-23");
        memberRepository.save(customer);

        //판매자
        Member seller = Member.createMember("seller@aaa.aaa", "1234", "이재원", "부천시", "원미로", "232-23");
        memberRepository.save(seller);

        //관리자
        Member admin = Member.createMember("admin@aaa.aaa", "1234", "임수", "부천시", "원미로", "232-23");
        admin.setRole(MemberRole.ADMIN);
        memberRepository.save(admin);

    }

    @Test
    public void 멤버_권한변경_요청_승인(){
        Member admin = memberService.createAdmin("admin@aaa.aaa", "1234", "임수", "부천시", "원미로", "232-23");

        // 고객 - 판매자로 변경 요청
        Member customer = memberService.createMember("customer@aaa.aaa", "1234", "이재원", "부천시", "원미로", "232-23");
        Request request = requestService.createRequest(customer.getId(), MemberRole.SELLER);

        // 어드민 - 승인
        memberService.approveRequest(admin.getId(), request.getId());
    }


}