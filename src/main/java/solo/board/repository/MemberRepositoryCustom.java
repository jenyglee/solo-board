package solo.board.repository;

import solo.board.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> search(String email);
}
