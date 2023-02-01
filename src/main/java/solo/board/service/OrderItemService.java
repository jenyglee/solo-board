package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.entity.Item;
import solo.board.entity.OrderItem;
import solo.board.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    public OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = OrderItem.createOrderItem(item, count);
        orderItemRepository.save(orderItem);
        return orderItem;
    }
}
