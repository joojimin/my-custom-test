package com.example.mycustomtest.member.dto;

import com.example.mycustomtest.member.domain.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
public class MemberRequest {

    private String name;
    private int age;
    private String address;

    public MemberRequest(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Member createMember() {
        return new Member(this.name,
                          this.age,
                          this.address);
    }
}
