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
import java.util.Optional;

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

    // 나의 주문 내역 조회
    public PageResponseDto<List<OrderResponseDto>> getOrderList(Long memberId, int offset, int limit){
        // 1. 페이징 적용하여 조회
        PageRequest pageRequest = PageRequest.of(offset, limit);
        Page<Order> results = orderRepository.findAllByMember_Id(memberId, pageRequest);

        // 2. 데이터, 전체 개수 추출
        List<Order> orderList = results.getContent();
        long totalElements = results.getTotalElements();

        // 3. 엔티티를 Dto로 변환
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderItem> orderItemList = order.getOrderItemList();
            List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();

            // 3-1. Dto 안에 있는 OrderItem 엔티티도 Dto로 변환
            for (OrderItem orderItem : orderItemList) {
                orderItemResponseDtoList.add(new OrderItemResponseDto(orderItem.getItem().getName(), orderItem.getCount()));
            }
            orderResponseDtoList.add(new OrderResponseDto(order.getId(), order.getMember().getNickName(), orderItemResponseDtoList));
        }

        // 4. 클라이언트에 응답
        return new PageResponseDto<>(offset, totalElements, orderResponseDtoList);
    }

    //✨나의 주문 삭제
    public void removeOrder(Long orderId, Long memberId){
        // Optional<Order> order = orderRepository.findByIdAndMember_Id(orderId, memberId).orElseThrow();
    }

}
