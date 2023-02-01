package solo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByMember_Id(Long memberId, Pageable pageable);
}
