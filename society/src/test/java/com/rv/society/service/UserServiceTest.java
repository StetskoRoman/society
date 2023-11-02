package com.rv.society.service;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import com.rv.society.repos.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


//@ExtendWith(SpringExtension.class)
//@WebAppConfiguration
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @Test
    void addUser() {
        User user = new User();

//        user.setEmail("some@mail.ru");

        boolean isUserCreated = userService.addUser(user);

        Assertions.assertTrue(isUserCreated);
//        Assertions.assertNotNull(user.getActivationCode());
//        Assertions.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
//
//        Mockito.verify(userRepo, Mockito.times(1)).save(user);
//        Mockito.verify(mailSender, Mockito.times(1))
//                .send(
//                        ArgumentMatchers.eq(user.getEmail()),
//                        ArgumentMatchers.anyString(),
//                        ArgumentMatchers.anyString()
//                );
    }

}