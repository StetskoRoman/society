package com.rv.society;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.rv.society.controllers.MainController;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

//@ExtendWith(SpringExtension.class) - даем знать в каком окружении стартуют тесты (в гайде без него можно)
//@SpringBootTest - пойдет по всему пути пакета и найдет весь контекст в основном приложении который пустит в тесте
//@AutoConfigureMockMvc - автоматически создает структуру классов которая подменяет слой MVC (всё в фэйковом окружении, не на реальных объектах)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
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

    public void correctLoginTest() throws Exception {

    }


}
