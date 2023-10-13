package com.rv.society.controllers;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import com.rv.society.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        //передаст все значения ролей
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

//    @PostMapping
//    public String userSave(
//            @RequestParam String username,
//            //в форме будут все поля юзера, на сервер попадали только те поля из ролей юзера которые с флажком отмеченным были
//            @RequestParam Map<String, String> form,
//            @RequestParam("userID") User user) {
//
//        user.setUsername(username);
//
//        //далее извлекли роли которые стояли у пользователя
//        Set<String> roles = Arrays.stream(Role.values())
//                .map(Role::name)
//                .collect(Collectors.toSet());
//
//        //очистить роли у юзера
//        user.getRoles().clear();
//
//        //перебираем форму по ключам, если в ролях пользователя была роль из всего списка ролей, то добавить ее к юзеру
//        for (String key : form.keySet()) {
//            if (roles.contains(key)) {
//                user.getRoles().add(Role.valueOf(key));  //enum
//            }
//        }
//        userRepo.save(user);
//
//        return "redirect:/user";
//    }
    @PostMapping()
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}
