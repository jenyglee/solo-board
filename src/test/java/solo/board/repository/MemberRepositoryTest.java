package solo.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.member.Admin;
import solo.board.entity.member.Customer;
import solo.board.entity.member.Member;
import solo.board.entity.MemberRole;
import solo.board.entity.Request;
import solo.board.entity.member.Seller;
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
        createCustomer();
        //판매자
        createSeller();
        //관리자
        createAdmin();
    }

    @Test
    public void 멤버_권한변경_요청_승인(){
        Member admin = createAdmin();

        // 고객 - 판매자로 변경 요청
        Customer customer = createCustomer();
        Request request = memberService.createRequest(MemberRole.SELLER, customer.getId());

        // 어드민 - 승인
        memberService.approveRequest(admin.getId(), request.getId());
    }

    private Admin createAdmin() {
        Admin admin = new Admin();
        admin.setBasicInfo("admin@asd.asd","1111","관리자");
        memberRepository.save(admin);
        return admin;
    }

    private Seller createSeller() {
        Seller seller = new Seller();
        seller.setBasicInfo("seller1@asd.asd","1111","판매자");
        seller.setAddress("부천시", "원미로", "232번길");
        memberRepository.save(seller);
        return seller;
    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setBasicInfo("member1@asd.asd","1111","고객");
        customer.setAddress("부천시", "원미로", "232번길");
        memberRepository.save(customer);
        return customer;
    }


}