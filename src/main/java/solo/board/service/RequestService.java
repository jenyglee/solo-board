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
public class RequestService {
    private final RequestRepository requestRepository;
    private final MemberRepository memberRepository;

    public Request createRequest(Long memberId, MemberRole role){
        // 멤버 확인
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if(optionalMember.isEmpty()){
            throw new IllegalArgumentException("계정을 찾을 수 없습니다.");
        }
        Request request = new Request(role, optionalMember.get());
        requestRepository.save(request);
        return request;
    }
}
