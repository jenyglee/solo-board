package solo.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.*;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class OrderRepositoryTest {
    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DeliveryRepository deliveryRepository;

    @Test
    public void 주문(){
        Member member = memberRepository.save(Member.createMember("member1", "1111", "", "부천시", "원미로", "232번길"));

        Item item1 = itemRepository.save(Item.createItem("딸기", 3000, 1000));
        Item item2 = itemRepository.save(Item.createItem("계란", 5000, 1000));
        Item item3 = itemRepository.save(Item.createItem("감자", 500, 1000));

        OrderItem orderItem1 = orderItemRepository.save(OrderItem.createOrderItem(item1, 21));
        OrderItem orderItem2 = orderItemRepository.save(OrderItem.createOrderItem(item2, 11));
        OrderItem orderItem3 = orderItemRepository.save(OrderItem.createOrderItem(item3, 8));

        Delivery delivery = deliveryRepository.save(Delivery.createDelivery(member.getAddress()));
        Order order = Order.createOrder(member, delivery, orderItem1, orderItem2, orderItem3);
        orderRepository.save(order);
    }

}