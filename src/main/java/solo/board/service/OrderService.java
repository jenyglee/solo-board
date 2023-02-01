package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import solo.board.dto.OrderItemResponseDto;
import solo.board.dto.OrderResponseDto;
import solo.board.dto.PageResponseDto;
import solo.board.entity.Delivery;
import solo.board.entity.Member;
import solo.board.entity.Order;
import solo.board.entity.OrderItem;
import solo.board.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    // 주문
    public Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = Order.createOrder(member, delivery, orderItems);
        orderRepository.save(order);
        return order;
    }

    //✨나의 주문 내역 조회
    public PageResponseDto<List<OrderResponseDto>> getOrderList(Long memberId, int offset, int limit){
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<Order> results = orderRepository.findAllByMember_Id(memberId, pageRequest);
        List<Order> orderList = results.getContent();
        long totalElements = results.getTotalElements();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderItem> orderItemList = order.getOrderItemList();
            List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                orderItemResponseDtoList.add(new OrderItemResponseDto(orderItem.getItem().getName(), orderItem.getCount()));
            }

            orderResponseDtoList.add(new OrderResponseDto(order.getId(), order.getMember().getNickName(), orderItemResponseDtoList));
        }
        return new PageResponseDto<>(offset, totalElements, orderResponseDtoList);
    }

    //✨나의 주문 삭제
    public void removeOrder(Long memberId){

    }

}
