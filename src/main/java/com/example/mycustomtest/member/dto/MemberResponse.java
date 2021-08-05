package com.example.mycustomtest.member.dto;

import com.example.mycustomtest.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponse {
    private Long id;
    private String name;
    private int age;
    private String address;

    @Builder
    public MemberResponse(Long id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public static MemberResponse createMemberResponse(final Member member) {
        return MemberResponse.builder()
                             .id(member.getId())
                             .name(member.getName())
                             .age(member.getAge())
                             .address(member.getAddress())
                             .build();
    }
}
