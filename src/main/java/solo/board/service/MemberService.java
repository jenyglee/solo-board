package solo.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solo.board.entity.member.Member;
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
        Long memberId = request.get().getMember().getId();
        Optional<Member> requestedMember = memberRepository.findById(memberId);
        if(requestedMember.isEmpty()){
            throw new IllegalArgumentException("요청한 유저를 찾을 수 없습니다.");
        }

        Member member = requestedMember.get();

        // requestRepository.deleteById(requestId);
        // MemberRole role = requestedMember.get().getRole();
        // role = MemberRole.SELLER;
    }
}
