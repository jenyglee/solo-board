package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
