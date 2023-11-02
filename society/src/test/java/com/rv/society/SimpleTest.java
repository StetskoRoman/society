package com.rv.society;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class SimpleTest {

    @Test
    public void test() {
        int x = 2;
        int y = 23;

        Assertions.assertEquals(46, x * y);
    }
}
