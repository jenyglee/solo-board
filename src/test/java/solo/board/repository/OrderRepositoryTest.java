package solo.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.entity.*;
import solo.board.entity.Member;
import solo.board.service.*;

import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
@Rollback(false)
class OrderRepositoryTest {
    @Autowired
    EntityManager em;
    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    OrderService orderService;

    @Test
    public void 주문(){
        Member seller = memberService.createMember("seller@aaa.aaa", "1234", "판매자", "부천시", "원미로", "232번길");
        seller.setRole(MemberRole.SELLER);
        Member customer = memberService.createMember("customer@aaa.aaa", "1234", "구매자", "부천시", "원미로", "232번길");

        Item item1 = itemService.createItem("딸기", 3000, 1000, seller);
        Item item2 = itemService.createItem("계란", 5000, 1000, seller);
        Item item3 = itemService.createItem("감자", 500, 1000, seller);

        OrderItem orderItem1 = orderItemService.createOrderItem(item1, 21);
        OrderItem orderItem2 = orderItemService.createOrderItem(item2, 11);
        OrderItem orderItem3 = orderItemService.createOrderItem(item3, 8);


        Delivery delivery = deliveryService.createDelivery(customer.getAddress());

        orderService.createOrder(customer, delivery, orderItem1, orderItem2, orderItem3);
    }

}