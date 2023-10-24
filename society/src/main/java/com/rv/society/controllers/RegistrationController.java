package com.rv.society.controllers;

import com.rv.society.domain.User;
import com.rv.society.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller

public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
//        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Passwords not equals");
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.addAttribute(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            //если в БД такой юзер существует то на страницу передадим сообзение
            model.addAttribute("usernameError", "User already exist!");
            return "registration";
        }

        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {

        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}



//далее если не было юзера, новый зарегается, переехало в юзер сервис
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        userRepo.save(user);