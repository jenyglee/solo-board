package solo.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.Item;
import solo.board.entity.Member;
import solo.board.entity.MemberRole;
import solo.board.service.ItemService;
import solo.board.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {
    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;
    @Test
    public void 판매상품_등록(){
        Member seller = memberService.createMember("seller@aaa.aaa", "1234", "판매자", "부천시", "원미로", "232번길");
        seller.setRole(MemberRole.SELLER);
        Item item1 = itemService.createItem("딸기", 3000, 1000, seller);
        Item item2 = itemService.createItem("계란", 5000, 1000, seller);
        Item item3 = itemService.createItem("감자", 500, 1000, seller);
    }

}