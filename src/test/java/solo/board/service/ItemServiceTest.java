package solo.board.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.dto.ItemRequestDto;
import solo.board.entity.Item;
import solo.board.entity.Member;
import solo.board.entity.MemberRole;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;
    @Test
    public void 판매상품_등록(){
        //given
        Member seller = memberService.createMember("seller@aaa.aaa", "1234", "판매자", "부천시", "원미로", "232번길");
        seller.setRole(MemberRole.SELLER);

        //when
        Item item1 = itemService.createItem("딸기", 3000, 1000, seller);

        //then
        assertThat(item1.getName()).isEqualTo("딸기");
        assertThat(item1.getPrice()).isEqualTo(3000);
        assertThat(item1.getStockQuantity()).isEqualTo(1000);
    }
    @Test
    public void 나의_판매상품_조회(){
        //given
        Member sellerA = memberService.createMember("sellerA@aaa.aaa", "1234", "판매자A", "부천시", "원미로", "232번길");
        Member sellerB = memberService.createMember("sellerB@aaa.aaa", "1234", "판매자B", "부천시", "원미로", "232번길");
        sellerA.setRole(MemberRole.SELLER);
        sellerB.setRole(MemberRole.SELLER);
        itemService.createItem("딸기", 3000, 1000, sellerA);
        itemService.createItem("계란", 5000, 1000, sellerA);
        itemService.createItem("감자", 500, 1000, sellerB);

        //when
        List<Item> itemList = itemService.getItemList(sellerA.getId());

        //then
        assertThat(itemList.get(0).getName()).isEqualTo("딸기");
        assertThat(itemList.get(1).getName()).isEqualTo("계란");
    }

    @Test
    public void 나의_판매상품_수정(){
        //given
        Member sellerA = memberService.createMember("sellerA@aaa.aaa", "1234", "판매자A", "부천시", "원미로", "232번길");
        sellerA.setRole(MemberRole.SELLER);
        Item item1 = itemService.createItem("딸기", 3000, 1000, sellerA);

        //when
        ItemRequestDto itemRequestDto = new ItemRequestDto("딸기2", 4000, 2000);
        itemService.editItem(sellerA, item1.getId(), itemRequestDto);

        //then
        assertThat(item1.getName()).isEqualTo("딸기2");
        assertThat(item1.getPrice()).isEqualTo(4000);
        assertThat(item1.getStockQuantity()).isEqualTo(2000);

    }

    @Test
    public void 나의_판매상품_삭제(){
        //given
        Member sellerA = memberService.createMember("sellerA@aaa.aaa", "1234", "판매자A", "부천시", "원미로", "232번길");
        sellerA.setRole(MemberRole.SELLER);
        Item item1 = itemService.createItem("딸기", 3000, 1000, sellerA);

        //when
        itemService.removeItem(sellerA, item1.getId());

        //then
        List<Item> itemList = itemService.getItemList(sellerA.getId());
        assertThat(itemList.size()).isEqualTo(0);
    }
}