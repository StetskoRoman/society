package com.rv.society;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DumbPasswordEncoderTest {



    @Test
    void encode() {
        DumbPasswordEncoder encoder = new DumbPasswordEncoder();
        Assertions.assertEquals("secret: 'mypwd'", encoder.encode("mypwd"));
    }
}