package com.example.mycustomtest.member.dto;

import com.example.mycustomtest.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberRequest {

    private String name;
    private int age;
    private String address;

    public Member createMember() {
        return new Member(this.name,
                          this.age,
                          this.address);
    }
}
