package solo.board.repository;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import solo.board.entity.Member;
import solo.board.entity.QMember;

import javax.persistence.EntityManager;
import java.util.List;

import static solo.board.entity.QMember.*;


public class MemberRepositoryImpl implements MemberRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Member> search(String email) {
        return queryFactory.select(member)
                .from(member)
                .where(member.email.eq(email))
                .fetch();
    }
}
