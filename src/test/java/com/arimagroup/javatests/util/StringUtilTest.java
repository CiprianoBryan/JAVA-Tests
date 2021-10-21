package com.arimagroup.javatests.util;

//import static org.junit.Assert.*;

public class StringUtilTest {
    public static void main(String[] args) {
        String result = StringUtil.repeat("hola", 3);
        assertEquals(result, "hola");
    }

    private static void assertEquals(String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new RuntimeException(actual + " is not equal to expected " + expected);
        }
    }
}