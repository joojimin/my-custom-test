package com.example.mycustomtest.member.ui;

import com.example.mycustomtest.member.dto.MemberRequest;
import com.example.mycustomtest.member.dto.MemberResponse;
import com.example.mycustomtest.support.AbstractMockMvcControllerTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends AbstractMockMvcControllerTest {

    @Autowired
    private MemberController memberController;

    @Override
    protected Object controller() {
        return this.memberController;
    }

    @Test
    void getMembers() throws Exception {
        // given
        Mockito.when(memberService.getMembers()).thenReturn(List.of(MemberResponse.builder().id(1L).name("주지민").age(30).address("구로구").build()));

        // when
        mockMvc.perform(get("/members"))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    void getMember() throws Exception {
        // given
        Mockito.when(memberService.getMember(1L)).thenReturn(MemberResponse.builder().id(1L).name("주지민").age(30).address("구로구").build());

        // when
        mockMvc.perform(get("/member/{id}", 1L))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    void saveMember() throws Exception {
        // given
        MemberRequest request = new MemberRequest("주지민", 30, "구로구");
        MemberResponse expected = MemberResponse.builder().id(1L).name("주지민").age(30).address("구로구").build();
        Mockito.when(memberService.saveMember(request)).thenReturn(expected);

        // when
        mockMvc.perform(post("/member")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(content().json(objectMapper.writeValueAsString(expected)))
               .andDo(print());
    }
}
