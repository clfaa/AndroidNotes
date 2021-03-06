package com.clfaa.androidnotes.rxjava;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by changlifei on 16/4/26.
 */
public class RxHelloWorldTest {

    @Test
    public void testOne() throws Exception {
        new RxHelloWorld().one();
    }

    @Test
    public void testTwo() throws Exception {
        new RxHelloWorld().two();
    }

    @Test
    public void testThree() throws Exception {
        new RxHelloWorld().three();
    }

    @Test
    public void testFour() throws Exception {
        new RxHelloWorld().four();
    }

    @Test
    public void testHelloMap() throws Exception {
        new RxHelloWorld().helloMap();
    }

    @Test
    public void testHelloForm() throws Exception {
        new RxHelloWorld().helloForm();
    }

    @Test
    public void testHelloFlatMap() throws Exception {
        new RxHelloWorld().helloFlatMap();
    }
}