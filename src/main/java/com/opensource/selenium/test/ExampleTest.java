package com.opensource.selenium.test;

import com.opensource.selenium.base.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest extends BaseTest {

    @Test
    public void test() {

        System.out.println("input : " + input);
        System.out.println("userName : " + data.get("userName"));
        System.out.println("password : " + data.get("password"));
        System.out.println("id : " + data.get("id"));

        //Assert.assertEquals(true, false);

        output = "232534";
    }

    @Ignore
    @Test
    public void test1() {

        System.out.println("userName : " + data.get("userName"));

    }
}