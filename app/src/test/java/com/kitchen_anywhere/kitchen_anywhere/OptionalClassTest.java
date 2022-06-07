package com.kitchen_anywhere.kitchen_anywhere;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OptionalClassTest {

    OptionalClass SUT;

    @Before
    public void setUp()  {

        SUT = new OptionalClass();

    }

    @Test
    public void isPositive() {

        Assert.assertTrue(SUT.isPositive(6));

    }
}