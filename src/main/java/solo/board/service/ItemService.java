package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.entity.Item;
import solo.board.entity.Member;
import solo.board.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item createItem(String name, int price, int quantity, Member seller){
        Item item = Item.createItem(name, price, quantity, seller);
        itemRepository.save(item);
        return item;
    }

}
