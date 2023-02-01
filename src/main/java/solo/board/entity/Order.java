package solo.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.board.entity.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity(name = "orders")
@Getter
@NoArgsConstructor
public class Order extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;
    private OrderStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.update(member, delivery, orderItems);
        return order;
    }

    public void update(Member member, Delivery delivery, OrderItem[] orderItems) {
        this.member = member;
        this.delivery = delivery;
        delivery.setOrder(this);
        this.status = OrderStatus.ORDER;
        for (OrderItem orderItem : orderItems) {
            addOrderItem(orderItem);
        }
    }
}
