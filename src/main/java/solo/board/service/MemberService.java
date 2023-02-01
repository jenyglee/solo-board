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
    public Request createRequest(MemberRole role, Member member){
        Request request = new Request(role, member);
        requestRepository.save(request);
        return request;
    }

    public void approveRequest(Long adminId, Long requestId) {
        Optional<Member> admin = memberRepository.findById(adminId);
        if(admin.isEmpty()){
            throw new IllegalArgumentException("계정을 찾을 수 없습니다.");
        }
        Optional<Request> request = requestRepository.findById(requestId);
        if(request.isEmpty()){
            throw new IllegalArgumentException("요청을 찾을 수 없습니다.");
        }

        Long memberId = request.get().getMember().getId();
        Optional<Member> requestedMember = memberRepository.findById(memberId);
        if(requestedMember.isEmpty()){
            throw new IllegalArgumentException("요청한 유저를 찾을 수 없습니다.");
        }
        System.out.println("requestedMember.get().getEmail() = " + requestedMember.get().getEmail());
        requestedMember.get().setRole(request.get().getRole());

        requestRepository.deleteById(requestId);
        // MemberRole role = requestedMember.get().getRole();
        // role = MemberRole.SELLER;
    }
}
