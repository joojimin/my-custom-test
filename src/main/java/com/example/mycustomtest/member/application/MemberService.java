package com.example.mycustomtest.member.application;

import com.example.mycustomtest.member.dto.MemberRequest;
import com.example.mycustomtest.member.dto.MemberResponse;
import com.example.mycustomtest.member.infrastructure.MemberRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponse> getMembers() {
        return memberRepository.findAll()
                               .stream()
                               .map(MemberResponse::createMemberResponse)
                               .collect(Collectors.toList());
    }

    public MemberResponse saveMember(final MemberRequest request) {
        return MemberResponse.createMemberResponse(memberRepository.save(request.createMember()));
    }
}
