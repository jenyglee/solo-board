package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.entity.Member;
import solo.board.entity.MemberRole;
import solo.board.entity.Request;
import solo.board.repository.MemberRepository;
import solo.board.repository.RequestRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;

    public Member createMember(String email, String password, String nickName, String city, String street, String zipcode){
        Member member = Member.createMember(email, password, nickName, city, street, zipcode);
        memberRepository.save(member);
        return member;
    }


    public Request createRequest(MemberRole role, Long memberid){
        Optional<Member> optionalMember = memberRepository.findById(memberid);
        if(optionalMember.isEmpty()){
            throw new IllegalArgumentException("계정을 찾을 수 없습니다.");
        }
        Request request = new Request(role, optionalMember.get());
        requestRepository.save(request);
        return request;
    }


    public void approveRequest(Long adminId, Long requestId) {
        // 어드민을 찾는다.
        Optional<Member> admin = memberRepository.findById(adminId);
        if(admin.isEmpty()){
            throw new IllegalArgumentException("계정을 찾을 수 없습니다.");
        }

        // 요청을 찾는다.
        Optional<Request> request = requestRepository.findById(requestId);
        if(request.isEmpty()){
            throw new IllegalArgumentException("요청을 찾을 수 없습니다.");
        }

        // 요청한 멤버를 찾는다.
        // Long memberId = request.get().getMember().getId();
        // Optional<Member> requestedMember = memberRepository.findById(memberId);
        // if(requestedMember.isEmpty()){
        //     throw new IllegalArgumentException("요청한 유저를 찾을 수 없습니다.");
        // }

        // Member member = requestedMember.get();
        // 커스토머 디비를 삭제
        // memberRepository.delete(); // << 커스토머 삭제되는지
        // // 셀러에 인서트
        // memberRepository.save(); // << 셀러로 가지는지

        // requestRepository.deleteById(requestId);
        // MemberRole role = requestedMember.get().getRole();
        // role = MemberRole.SELLER;
    }
}
