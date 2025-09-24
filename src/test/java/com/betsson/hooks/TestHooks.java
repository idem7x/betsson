package com.betsson.hooks;

import com.betsson.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class TestHooks {

    @Before
    public void setUp() {
        DriverManager.initializeDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}