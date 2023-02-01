package solo.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import solo.board.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
