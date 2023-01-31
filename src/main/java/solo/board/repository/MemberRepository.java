package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
