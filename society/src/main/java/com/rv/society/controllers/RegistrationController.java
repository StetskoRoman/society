package com.rv.society.controllers;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import com.rv.society.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            //если в БД такой юзер существует то на страницу передадим сообзение
            model.put("message", "User already exist!");
            return "registration";
        }
    //далее если не было юзера, новый зарегается
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
