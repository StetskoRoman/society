package com.rv.society.controllers;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import com.rv.society.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
//@RequiredArgsConstructor
@RequestMapping("/user")
//@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        //передаст все значения ролей
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }

    //просмотр профиля    на вход модель в которые ложим данные, и будет ожидать пользователя из контекста чтобы не получать его из БД
    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {

        userService.updateProfile(user, password, email);

        return "redirect:/user/profile";
    }

    @GetMapping("/subscribe/{user}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        userService.subscribe(currentUser, user);

        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("/unsubscribe/{user}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ) {
        userService.unsubscribe(currentUser, user);

        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("/{type}/{user}/list")
    public String userList(
            Model model,
            @PathVariable User user,
            @PathVariable String type
    ) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        return "subscriptions";
    }
}
//    @GetMapping("subscribe/{user}")
//    public String subscribe(
//            @AuthenticationPrincipal User currentUser,
//            @PathVariable User user
//    ) {
//        userService.subscribe(currentUser, user);
//
//        return "redirect:/user-messages/" + user.getId();
//    }
//
//    @GetMapping("unsubscribe/{user}")
//    public String unsubscribe(
//            @AuthenticationPrincipal User currentUser,
//            @PathVariable User user
//    ) {
//        userService.unsubscribe(currentUser, user);
//
//        return "redirect:/user-messages/" + user.getId();
//    }
//
//    //сюда юзерChanel, тип и юзеров
//    @GetMapping("{type}/{user}/list")
//    public String userList(
//            Model model,
//            @PathVariable User user,
//            @PathVariable String type
//    ) {
//        model.addAttribute("userChannel", user);
//        model.addAttribute("type", type);
//
//        if ("subscriptions".equals(type)) {
////            мои подписки (т.е. я на кого подписан)
//            model.addAttribute("users", user.getSubscriptions());
//        } else {
////            те кто подписан на меня
//            model.addAttribute("users", user.getSubscribers());
//        }
//        return "subscriptions";
//    }




// user.setUsername(username);
//
//         Set<String> roles = Arrays.stream(Role.values())
//        .map(Role::name)
//        .collect(Collectors.toSet());
//
//        user.getRoles().clear();
//
//        for (String key : form.keySet()) {
//        if (roles.contains(key)) {
//        user.getRoles().add(Role.valueOf(key));
//        }
//        }
//
//        userRepo.save(user);

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