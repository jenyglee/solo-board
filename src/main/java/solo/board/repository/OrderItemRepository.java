package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
