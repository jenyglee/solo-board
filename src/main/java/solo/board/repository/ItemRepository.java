package solo.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Item;
import solo.board.entity.Member;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByMemberId(Long id, Pageable pageable);
    Optional<Item> findByIdAndMember_Id(Long itemId, Long memberId);
}
