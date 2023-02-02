package solo.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solo.board.dto.ItemRequestDto;
import solo.board.dto.ItemResponseDto;
import solo.board.dto.PageResponseDto;
import solo.board.entity.Member;
import solo.board.entity.MemberRole;
import solo.board.service.ItemService;
import solo.board.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final MemberService memberService;
    // 판매상품 등록
    @PostMapping("/item")
    public ResponseEntity createItem(){
        Member sellerA = memberService.createMember("sellerA@aaa.aaa", "1234", "판매자A", "부천시", "원미로", "232번길");
        Member sellerB = memberService.createMember("sellerB@aaa.aaa", "1234", "판매자B", "부천시", "원미로", "232번길");
        sellerA.setRole(MemberRole.SELLER);
        sellerB.setRole(MemberRole.SELLER);
        itemService.createItem("딸기", 3000, 1000, sellerA);
        itemService.createItem("계란", 5000, 1000, sellerA);
        itemService.createItem("김치", 5000, 1000, sellerA);
        itemService.createItem("족발", 5000, 1000, sellerA);
        itemService.createItem("샤프", 5000, 1000, sellerA);
        itemService.createItem("햄", 5000, 1000, sellerA);
        itemService.createItem("감자", 500, 1000, sellerB);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 판매 상품 조회
    @GetMapping("/item")
    public ResponseEntity getItemList(@RequestParam Long memberId){
        PageResponseDto<List<ItemResponseDto>> itemList = itemService.getItemList(memberId, 2, 2);
        return new ResponseEntity(itemList, HttpStatus.OK);
    }

    // 판매 상품 수정
    @PutMapping("/item/{id}")
    public void editItem(@PathVariable Long id, @RequestParam Long memberId){
        ItemRequestDto itemRequestDto = new ItemRequestDto("딸기2", 4000, 2000);
        itemService.editItem(memberId, id, itemRequestDto);
    }

    // 판매 상품 삭제
}
