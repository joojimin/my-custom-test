package com.example.mycustomtest.jupiter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assumptions.*;

public class jupiterAssumptions {

    private final String env = "dev";

//    @Test
//    void assumeTrueTest1() {
//        assumeTrue(env.equals("local"));
//        assertThat("hi1".equals("hi1")).isTrue();
//    }
//
//    @Test
//    void assumeTrueTest2() {
//        assumeTrue(env.equals("dev"));
//        assertThat("hi2".equals("hi2")).isTrue();
//    }

//    @Test
//    void assumeTrueTest1() {
//        assumeTrue(() -> false, "hello test");
//    }

    @Test
    void assumeFalseTest1() {
        assumeFalse(env.equals("local"));
        assertThat("hi".equals("hi")).isTrue();
    }


    @Test
    void assumeThatTest1() {
        assumingThat(env.equals("local"),
                     () -> assertThat("hi".equals("hi2")).isTrue());

        assertThat("hi".equals("hi")).isTrue();
    }

    @Test
    void assertJassumeThatTest() {
        assumeThat(env).isEqualTo("dev");
        assertThat("hi".equals("hi")).isTrue();
    }

}
