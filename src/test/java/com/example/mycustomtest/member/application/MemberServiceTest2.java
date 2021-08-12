package com.example.mycustomtest.member.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.mock;

import com.example.mycustomtest.member.domain.Member;
import com.example.mycustomtest.member.dto.MemberRequest;
import com.example.mycustomtest.member.dto.MemberResponse;
import com.example.mycustomtest.member.infrastructure.MemberRepository;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MemberServiceTest2 {

    private MemberRepository memberRepository;
    private MemberService memberService;

    @BeforeEach
    void setUpTest() {
        memberRepository = mock(MemberRepository.class);
        memberService = new MemberService(memberRepository);
    }

    @DisplayName("멤버 전체 조회")
    @Test
    void getMembersTest() {
        // given
        Mockito.when(memberRepository.findAll()).thenReturn(
            List.of(Member.builder().name("주지민").age(30).address("구로구").build(),
                    Member.builder().name("주지민").age(31).address("광진구").build()));

        // when
        List<MemberResponse> actual = memberService.getMembers();

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).extracting("name","age","address")
                          .contains(tuple("주지민",30,"구로구"),
                                    tuple("주지민",31,"광진구"));

        Mockito.verify(memberRepository).findAll();
    }

    @DisplayName("멤버 저장")
    @Test
    void saveMemberTest() {
        // given
        MemberRequest memberRequest = new MemberRequest("주지민", 30, "구로구");
        Mockito.when(memberRepository.save(Mockito.any())).thenReturn(Member.builder()
                                                                            .name("주지민")
                                                                            .age(30)
                                                                            .address("구로구")
                                                                            .build());

        // when
        MemberResponse actual = memberService.saveMember(memberRequest);

        // then
        assertThat(actual).isNotNull();
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(actual.getName()).isEqualTo("주지민");
            assertThat(actual.getAge()).isEqualTo(30);
            assertThat(actual.getAddress()).isEqualTo("구로구");
        });
    }

}
