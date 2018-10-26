package com.baijunyu.annotations;

import com.baijunyu.annotations.exam01.TestNullness;

import org.junit.Test;

/**
 * Created by 95190 on 2018/6/20.
 */
public class TestNullnessTest {
    @Test
    public void test1() throws Exception {
        new TestNullness().test1();
    }

}