package com.example.mycustomtest.jupiter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class jupiterOnCondition {


    @EnabledOnOs(OS.MAC)
    @Test
    void testMac() {
        String os = "mac";
        System.out.println(os);
    }

    @EnabledOnOs(OS.WINDOWS)
    @Test
    void testWindow() {
        String os = "windows";
        System.out.println(os);
    }

    @EnabledOnJre(JRE.JAVA_8)
    @Test
    void testJava8() {
        String jre = "java8";
        System.out.println(jre);
    }

    @EnabledOnJre(JRE.JAVA_11)
    @Test
    void testJava11() {
        String jre = "java11";
        System.out.println(jre);
    }

    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
    @Test
    void testJava8to11() {
        String jre = "java8~11";
        System.out.println(jre);
    }


    @Test
    @EnabledIf("customCondition")
    void enabled() {
        System.out.println("enabled");
    }

    @Test
    @DisabledIf("customCondition")
    void disabled() {
        System.out.println("disabled");
    }

    boolean customCondition() {
        return true;
    }

}
