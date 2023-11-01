package com.rv.society;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rv.society.controllers.MainController;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

//@ExtendWith(SpringExtension.class) - даем знать в каком окружении стартуют тесты (в гайде без него можно)
//@SpringBootTest - пойдет по всему пути пакета и найдет весь контекст в основном приложении который пустит в тесте
//@AutoConfigureMockMvc - автоматически создает структуру классов которая подменяет слой MVC (всё в фэйковом окружении, не на реальных объектах)
//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ApplicationRunner.class})
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class LoginTest {

    @Autowired
    private MainController controller;
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void test() throws Exception {
//        assertThat(controller).isNotNull();
//    }

    @Test
    public void testHello() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, user")))
                .andExpect(content().string(containsString("Enter")));
    }

    //  Тест на перенаправление на логин если пытаешься зайти неавторизованным
    @Test
    public void loginTest() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void correctLoginTest() throws Exception {
//        this.mockMvc.perform(post("/login").param("username", "admin").param("password", "1"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
        this.mockMvc.perform(formLogin().user("dru").password("1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
//        this.mockMvc.perform(formLogin().user("A").password("1"))
//                .andDo(print())
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentials() throws Exception {
        this.mockMvc.perform(post("/login").param("username", "jonh"))
                .andDo(print())
                .andExpect(status().isForbidden());   //403 status
    }


}
