package com.example.mycustomtest.jupiter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class jupiterDisabled {


    @Test
    void testSuccess() {
        assertThat("hi").isEqualTo("hi");
    }

    @Disabled("안하는 테스트")
    @Test
    void testFailed() {
        assertThat("hi").isEqualTo("hi2");
    }

}
