package solo.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.service.MemberService;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 멤버_생성(){
        //고객

        //판매자

        //관리자

    }

    @Test
    public void 멤버_권한변경_요청_승인(){
        // Member admin = createAdmin();
        //
        // // 고객 - 판매자로 변경 요청
        // Customer customer = createCustomer();
        // Request request = customerService.createRequest(MemberRole.SELLER, customer.getId());
        //
        // // 어드민 - 승인
        // memberService.approveRequest(admin.getId(), request.getId());
    }


}