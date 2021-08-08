package com.example.mycustomtest.member.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.mycustomtest.member.domain.Member;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class MemberResponseTest {

    @Test
    void createMemberResponse() {
        // given
        Member member = Member.builder()
                              .name("주지민")
                              .age(30)
                              .address("구로구")
                              .build();

        // when
        MemberResponse memberResponse = MemberResponse.createMemberResponse(member);

        // then
        assertThat(memberResponse).isNotNull();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(memberResponse.getName()).isEqualTo("주지민");
            softAssertions.assertThat(memberResponse.getAge()).isEqualTo(30);
            softAssertions.assertThat(memberResponse.getAddress()).isEqualTo("구로구");
        });
    }
}
