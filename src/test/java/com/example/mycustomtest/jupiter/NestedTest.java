package com.example.mycustomtest.jupiter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

class NestedTest {

    @BeforeAll
    static void beforeClass() {
        System.out.println("hi beforeAll");
    }

    @AfterAll
    static void afterClass() {
        System.out.println("hi afterAll");
    }


    @Test
    void test() {
        System.out.println("test contents");
    }

    @TestInstance(Lifecycle.PER_CLASS)
    @Nested
    class innerClass {

        @BeforeAll
        void beforeClass() {
            System.out.println("bye beforeAll");
        }

        @AfterAll
        void afterClass() {
            System.out.println("bye afterAll");
        }

        @Test
        void test() {
            System.out.println("test22 contents");
        }
    }


    @Test
    void test3() {
        System.out.println("test3 contents");
    }

}
