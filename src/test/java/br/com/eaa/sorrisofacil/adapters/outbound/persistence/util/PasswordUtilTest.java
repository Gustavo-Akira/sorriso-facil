package br.com.eaa.sorrisofacil.adapters.outbound.persistence.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PasswordUtilTest {
    @Autowired
    private PasswordUtil util;
    @Test
    void encode() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = util.encode("akira");
        System.out.println(password);
        assertNotNull(password);
    }

    @Test
    void validatePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
        String passwordHashed = util.encode("akira");
        assertTrue(util.validatePassword("akira",passwordHashed));
    }
}