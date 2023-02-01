package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import solo.board.dto.ItemRequestDto;
import solo.board.dto.ItemResponseDto;
import solo.board.dto.PageResponseDto;
import solo.board.entity.Item;
import solo.board.entity.Member;
import solo.board.repository.ItemRepository;

import java.util.ArrayList;
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
    public PageResponseDto<List<ItemResponseDto>> getItemList(Long sellerId, int offset, int limit){
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<Item> results = itemRepository.findByMemberId(sellerId, pageRequest);
        List<Item> itemList = results.getContent();
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for (Item item : itemList) {
            itemResponseDtoList.add(new ItemResponseDto(item.getId(), item.getName(), item.getPrice() , item.getStockQuantity()));
        }
        long totalElements = results.getTotalElements();
        return new PageResponseDto<>(offset, totalElements, itemResponseDtoList);
    }

    // 판매상품 수정
    public void editItem(Long memberId, Long itemId, ItemRequestDto requestDto){
        // 1. 나의 상품인지 확인
        Item item = itemRepository.findByIdAndMember_Id(itemId, memberId).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );

        // 2. 상품 수정
        item.update(requestDto.getName(), requestDto.getPrice(), requestDto.getStockQuantity());
    }

    // 판매상품 삭제
    public void removeItem(Long memberId, Long itemId){
        // 1. 나의 상품인지 확인
        Item item = itemRepository.findByIdAndMember_Id(itemId, memberId).orElseThrow(
                () -> new IllegalArgumentException("상품을 찾을 수 없습니다.")
        );

        // 2. 상품 삭제
        itemRepository.delete(item);
    }
}
