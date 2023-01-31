package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
