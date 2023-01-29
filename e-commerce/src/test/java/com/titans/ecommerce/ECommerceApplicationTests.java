package com.titans.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class ECommerceApplicationTests {

    @Autowired
    PasswordEncoder encoder;

    @Test
    void contextLoads() {

    }

    @Test
    void testEncoder() {
        String encoded1 = encoder.encode("mypass");
        String encoded2 = encoder.encode("mypass");
        String encoded3 = encoder.encode("mypass2");
        assert encoder.matches("mypass", encoded1);
        assert !encoder.matches("mypasss", encoded1);
        assert !encoder.matches("mypass", encoded3);
        assert encoder.matches("mypass", encoded2);
    }

}
