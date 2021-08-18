package com.example.mycustomtest.member.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.times;

import com.example.mycustomtest.member.domain.Member;
import com.example.mycustomtest.member.dto.MemberRequest;
import com.example.mycustomtest.member.dto.MemberResponse;
import com.example.mycustomtest.member.infrastructure.MemberRepository;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Captor
    ArgumentCaptor<Member> memberArgumentCaptor;


    @DisplayName("멤버 전체 조회")
    @Test
    void getMembersTest() {
        // given
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(Member.builder().name("주지민").age(30).address("구로구").build(),
                                                                    Member.builder().name("주지민").age(31).address("광진구").build()));

        // when
        List<MemberResponse> actual = memberService.getMembers();

        // then
        assertThat(actual).isNotEmpty();
        assertThat(actual).extracting("name","age","address")
                          .contains(tuple("주지민",30,"구로구"),
                                    tuple("주지민",31,"광진구"));

        Mockito.verify(memberRepository, times(1)).findAll();
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

    @Test
    void captorTest() {
        // given
        MemberRequest memberRequest1 = new MemberRequest("주지민", 30, "구로구");
        MemberRequest memberRequest2 = new MemberRequest("주지민", 35, "성남시");
        Mockito.when(memberRepository.save(Mockito.any())).thenReturn(Member.builder()
                                                                            .name("주지민")
                                                                            .age(30)
                                                                            .address("구로구")
                                                                            .build());

        // when
        MemberResponse actual = memberService.saveCaptorTest(memberRequest1, memberRequest2);

        // then
        assertThat(actual).isNotNull();
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(actual.getName()).isEqualTo("주지민");
            assertThat(actual.getAge()).isEqualTo(30);
            assertThat(actual.getAddress()).isEqualTo("구로구");
        });

//        ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
        Mockito.verify(memberRepository).save(memberArgumentCaptor.capture());
        Member verify = memberArgumentCaptor.getValue();
        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(verify.getName()).isEqualTo(memberRequest1.getName());
            assertThat(verify.getAddress()).isEqualTo(memberRequest1.getAddress());
            assertThat(verify.getAge()).isEqualTo(memberRequest1.getAge());
        });
    }
}
