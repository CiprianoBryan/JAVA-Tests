package com.arimagroup.javatests.util;

//import static org.junit.Assert.*;

public class StringUtilTest {
    public static void main(String[] args) {
        String result = StringUtil.repeat("hola", 3);
        if (result.equals("holaholahola")) {
            System.out.println("OK");
        }
    }
}