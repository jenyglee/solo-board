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
    public void createOrder(){
        Member member = Member.createMember("member1", "1111", "", "부천시", "원미로", "232번길");
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId()).get();

        Item item1 = Item.createItem("딸기", 3000, 1000);
        Item item2 = Item.createItem("계란", 5000, 1000);
        Item item3 = Item.createItem("감자", 500, 1000);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        Item findItem1 = itemRepository.findById(item1.getId()).get();
        Item findItem2 = itemRepository.findById(item1.getId()).get();
        Item findItem3 = itemRepository.findById(item1.getId()).get();

        OrderItem orderItem1 = OrderItem.createOrderItem(item1, 21);
        OrderItem orderItem2 = OrderItem.createOrderItem(item2, 11);
        OrderItem orderItem3 = OrderItem.createOrderItem(item3, 8);
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        orderItemRepository.save(orderItem3);
        OrderItem findOrderItem1 = orderItemRepository.findById(orderItem1.getId()).get();
        OrderItem findOrderItem2 = orderItemRepository.findById(orderItem2.getId()).get();
        OrderItem findOrderItem3 = orderItemRepository.findById(orderItem3.getId()).get();

        Delivery delivery = Delivery.createDelivery(member.getAddress());
        deliveryRepository.save(delivery);
        Delivery findDelivery = deliveryRepository.findById(delivery.getId()).get();

        // System.out.println("member1 = " + member1.getId());
        // System.out.println("delivery = " + delivery.getId());
        // System.out.println("orderItem1 = " + orderItem1.getId());
        // System.out.println("orderItem2 = " + orderItem2.getId());
        // System.out.println("orderItem3 = " + orderItem3.getId());
        //
        // Order order = new Order(member, delivery, findOrderItem1, findOrderItem2, findOrderItem3);

        // System.out.println("order.getMember().getEmail() = " + order.getMember().getEmail());
        // System.out.println("order.getDelivery().getAddress().getCity() = " + order.getDelivery().getAddress().getCity());
        // Order order = new Order(findMember, findDelivery, findOrderItem1, findOrderItem2, findOrderItem3);

        Order order = Order.createOrder(member, delivery, orderItem1, orderItem2, orderItem3);
        Order saveOrder = orderRepository.save(order);


        // Assertions.assertThat(saveMember).isEqualTo(member1);
    }

}