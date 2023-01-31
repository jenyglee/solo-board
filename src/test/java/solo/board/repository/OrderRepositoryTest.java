package solo.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;
    @Test
    public void createOrder(){
        Member member1 = Member
                .createMember(
                    "member1",
                    "1111",
                    "",
                    "부천시",
                    "원미로",
                    "232번길"
                );
        Member saveMember = memberRepository.save(member1);

        Item item1 = Item
                .createItem(
                    "딸기",
                    3000,
                    1000
                );
        itemRepository.save(item1);

        OrderItem orderItem = OrderItem.createOrderItem(item1, 21);
        OrderItem saveOrderItem = orderItemRepository.save(orderItem);

        Delivery delivery = Delivery.createDelivery(member1.getAddress());

        Order.createOrder(member1, )

        Assertions.assertThat(saveMember).isEqualTo(member1);
    }

}