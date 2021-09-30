package com.example.mycustomtest.embeddeddb;

import static org.assertj.core.api.Assertions.*;

import com.example.mycustomtest.member.domain.Member;
import com.example.mycustomtest.member.infrastructure.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleMemberTest extends EmbeddedDbSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void test() {
        // given
        memberRepository.save(new Member("주지민1", 31, "서울시"));
        memberRepository.save(new Member("주지민2", 31, "서울시"));
        memberRepository.save(new Member("주지민3", 31, "서울시"));

        // when
        List<Member> actual = memberRepository.findAll();

        // then
        assertThat(actual)
            .hasSize(3)
            .extracting("name")
            .containsExactly("주지민1", "주지민2", "주지민3");
    }
}
