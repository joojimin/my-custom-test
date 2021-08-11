package com.example.mycustomtest.support;

import com.example.mycustomtest.member.application.MemberService;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MockBeanFactory {

    @MockBean
    protected MemberService memberService;

}
