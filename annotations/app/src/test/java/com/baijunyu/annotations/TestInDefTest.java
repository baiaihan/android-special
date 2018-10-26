package com.baijunyu.annotations;

import com.baijunyu.annotations.exam01.TestInDef;

import org.junit.Test;

/**
 * Created by 95190 on 2018/6/15.
 */
public class TestInDefTest {
    @Test
    public void test1() throws Exception {
        TestInDef testInDef = new TestInDef();
        testInDef.test1();
    }

}