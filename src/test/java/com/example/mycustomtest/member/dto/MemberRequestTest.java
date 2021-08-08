package com.example.mycustomtest.member.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

import com.example.mycustomtest.member.domain.Member;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class MemberRequestTest {

    @Test
    void createMember() {
        // given
        MemberRequest memberRequest = new MemberRequest("주지민", 30, "구로구");

        // when
        Member member = memberRequest.createMember();

        // then
        assertThat(member).isNotNull();
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(member.getName()).isEqualTo("주지민");
            softAssertions.assertThat(member.getAge()).isEqualTo(30);
            softAssertions.assertThat(member.getAddress()).isEqualTo("구로구");
        });
    }
}
