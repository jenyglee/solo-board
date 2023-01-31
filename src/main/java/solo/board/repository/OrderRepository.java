package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
