package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
