package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.dto.ItemRequestDto;
import solo.board.entity.Item;
import solo.board.entity.Member;
import solo.board.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // 판매상품 등록
    public Item createItem(String name, int price, int quantity, Member seller){
        Item item = Item.createItem(name, price, quantity, seller);
        itemRepository.save(item);
        return item;
    }

    // 나의 판매 상품 조회
    public List<Item> getItemList(Long sellerId){
        List<Item> itemList = itemRepository.findByMemberId(sellerId);
        return itemList;
    }

    // 판매상품 수정
    public void editItem(Member member, Long itemId, ItemRequestDto requestDto){
        // 나의 상품인지 확인
        Item item = itemRepository.findByIdAndMember(itemId, member).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );

        //상품 수정
        item.update(requestDto.getName(), requestDto.getPrice(), requestDto.getStockQuantity());
    }

    // 판매상품 삭제
    public void removeItem(Member member, Long itemId){
        // 나의 상품인지 확인
        Item item = itemRepository.findByIdAndMember(itemId, member).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );

        // 상품 삭제
        itemRepository.delete(item);
    }
}
