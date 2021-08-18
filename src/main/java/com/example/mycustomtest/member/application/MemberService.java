package com.example.mycustomtest.member.application;

import com.example.mycustomtest.member.domain.Member;
import com.example.mycustomtest.member.dto.MemberRequest;
import com.example.mycustomtest.member.dto.MemberResponse;
import com.example.mycustomtest.member.infrastructure.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponse> getMembers() {
        return memberRepository.findAll()
                               .stream()
                               .map(MemberResponse::createMemberResponse)
                               .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberResponse getMember(final Long id) {
       return MemberResponse.createMemberResponse(findMemberById(id));
    }

    private Member findMemberById(final Long id) {
        return memberRepository.findById(id)
                               .orElseThrow(() -> new IllegalArgumentException());
    }

    public MemberResponse saveMember(final MemberRequest request) {
        return MemberResponse.createMemberResponse(memberRepository.save(request.createMember()));
    }

    public MemberResponse saveCaptorTest(final MemberRequest request, final MemberRequest test) {
        test.setName("asdasd");
        return MemberResponse.createMemberResponse(memberRepository.save(request.createMember()));
    }
}
