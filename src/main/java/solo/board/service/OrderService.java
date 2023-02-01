package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.entity.Delivery;
import solo.board.entity.Member;
import solo.board.entity.Order;
import solo.board.entity.OrderItem;
import solo.board.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = Order.createOrder(member, delivery, orderItems);
        orderRepository.save(order);
        return order;
    }
}
