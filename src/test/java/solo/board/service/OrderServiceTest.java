package solo.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import solo.board.dto.OrderItemResponseDto;
import solo.board.dto.OrderResponseDto;
import solo.board.dto.PageResponseDto;
import solo.board.entity.*;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class OrderServiceTest {
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

    @Test
    public void 나의_주문내역_조회(){
        Member seller = memberService.createMember("seller@aaa.aaa", "1234", "판매자", "부천시", "원미로", "232번길");
        seller.setRole(MemberRole.SELLER);
        Item item1 = itemService.createItem("딸기", 3000, 1000, seller);
        Item item2 = itemService.createItem("계란", 5000, 1000, seller);
        Item item3 = itemService.createItem("감자", 500, 1000, seller);
        Item item4 = itemService.createItem("샤프", 500, 1000, seller);
        Item item5 = itemService.createItem("배", 500, 1000, seller);
        Item item6 = itemService.createItem("참치", 500, 1000, seller);

        Member customerA = memberService.createMember("customerA@aaa.aaa", "1234", "구매자", "부천시", "원미로", "232번길");
        Member customerB = memberService.createMember("customerB@aaa.aaa", "1234", "구매자", "부천시", "원미로", "232번길");
        Delivery delivery = deliveryService.createDelivery(customerA.getAddress());

        //첫번째 주문
        OrderItem orderItem1 = orderItemService.createOrderItem(item1, 21);
        orderService.createOrder(customerA, delivery, orderItem1);

        //두번째 주문
        OrderItem orderItem2 = orderItemService.createOrderItem(item2, 11);
        OrderItem orderItem3 = orderItemService.createOrderItem(item3, 8);
        orderService.createOrder(customerA, delivery, orderItem2, orderItem3);

        //세번째 주문
        OrderItem orderItem4 = orderItemService.createOrderItem(item4, 2);
        orderService.createOrder(customerA, delivery, orderItem4);

        //네번째 주문
        OrderItem orderItem5 = orderItemService.createOrderItem(item5, 1);
        OrderItem orderItem6 = orderItemService.createOrderItem(item6, 9);
        orderService.createOrder(customerB, delivery, orderItem5, orderItem6);

        //when
        PageResponseDto<List<OrderResponseDto>> orderList = orderService.getOrderList(customerA.getId(), 0, 2);
        List<OrderResponseDto> data = orderList.getData();
        for (OrderResponseDto orderResponseDto : data) {
            System.out.println("orderResponseDto = " + orderResponseDto);
        }
    }

    @Test
    public void 나의_주문_삭제(){

    }
}