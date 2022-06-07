package com.kitchen_anywhere.kitchen_anywhere;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignupTest {

    Signup SUT;

    @Before
    public void setUp() {
        SUT  = new Signup();
        SUT.fullName.getEditText().setText("aaa");
    }

    @Test
    public void NameValidator() {
        Assert.assertSame("aaa",SUT.fullName.getEditText().getText().toString());

    }
}