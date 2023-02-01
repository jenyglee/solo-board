package solo.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import solo.board.entity.Member;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Request(MemberRole role, Member member) {
        this.role = role;
        this.member = member;
    }
}
