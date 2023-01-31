package solo.board.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Order extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;
    private OrderStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(cascade = CascadeType.ALL, fetch = LAZY)
    private Delivery delivery;

    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    // private List<OrderItem> orderItemList;

    // Delivery, Item 객체가 만들어진 후 생성자 함수 제작
}
