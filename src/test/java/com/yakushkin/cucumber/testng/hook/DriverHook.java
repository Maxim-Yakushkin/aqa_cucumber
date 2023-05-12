package com.yakushkin.cucumber.testng.hook;

import io.cucumber.java.Before;

public class DriverHook {

    @Before
    public static void initMessage() {
        System.out.println("Cucumber was started successfully");
    }
}
