package com.kitchen_anywhere.kitchen_anywhere;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

    Cart SUT;

    @Before
    public void setUp(){
        SUT = new Cart();
    }

    @Test
    public void getTotalFee() {
        Assert.assertTrue(SUT.getTotalFee() > 0);
    }


}