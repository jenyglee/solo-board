package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Item;
import solo.board.entity.Member;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByMemberId(Long id);
    Optional<Item> findByIdAndMember(Long itemId, Member member);
}
