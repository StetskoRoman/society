package com.rv.society.controllers;

import com.rv.society.domain.Message;
import com.rv.society.domain.User;
import com.rv.society.domain.dto.MessageDto;
import com.rv.society.repos.MessageRepo;
import com.rv.society.service.MessageService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

//@Validated - сделает что надо но будет просто ошибка возвращаться при ошибке валидации где либо
//@Validated
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MessageRepo messageRepo;

    @Autowired
    private MessageService messageService;

    //   @Value("${upload.path}") говорим что хотим получить переменную из контекста в пропертис, т. е.  uploadPath будет upload.path=/Program/Projects/MyProjects/Twitter imitation/society/src/main/resources/static/files
    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable,
             @AuthenticationPrincipal User user
    ) {
        Page<MessageDto> page = messageService.messageList(pageable, filter, user);

        model.addAttribute("page", page);
        model.addAttribute("url", "/main");
        model.addAttribute("filter", filter);

        return "main";
    }



    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
//     BindingResult bindingResult хранит список аргументов и значения ошибок валидации, Всегда идет только ПЕРЕД Model model,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) throws IOException {
        //взяли из введенной в форму текст и тег, сохранили в БД
        message.setAuthor(user);
//  если у BindingResult ошибки то не пойдет дальше метод
        if (bindingResult.hasErrors()) {

            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
//            положили ошибки в модель
            model.mergeAttributes(errorMap);
            model.addAttribute("message", message);

        } else {
            saveFile(message, file);

            model.addAttribute("message", null);
            messageRepo.save(message);
        }
        model.addAttribute("url", "/main");
        Page<MessageDto> page = this.messageService.messageList(pageable, "", user); // пустой фильтр
        model.addAttribute("page", page);
//засунули в форму результат, т.е. в списке всех сообщений добавиться новое введенное
//        Iterable<Message> messages = messageRepo.findAll();
//        model.addAttribute("messages", messages);
        return "redirect:/main";
    }

    private void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
//            Обезопасим проверкой есть ли папка для загрузки файлов
                uploadDir.mkdir();
            }
//     Здесь страхуемся от коллизии UUID = universe unic id, каждый файл будет отличным, хотя лучше бы отдельное репо под картинки
            String uuidFile = UUID.randomUUID().toString();
            System.out.println("uuidFile " + uuidFile);
            String resultFileName = uuidFile + ".jpg" + file.getOriginalFilename();
            System.out.println("resultFileName " + resultFileName);
            file.transferTo(new File(uploadPath + "/" + resultFileName));  //загружаем файл
            message.setFilename(resultFileName);
        }
    }

    // {user} сюда вставиться    @PathVariable User user        (@PathVariable(name = "user") если переменные не совпадают
//     @RequestParam Message message  из юрла возьмет значение самого сообщения

    @GetMapping("/user-messages/{author}")
    public String userMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User author,
            Model model,
            @RequestParam(required = false) Message message,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
//        Здесь сообщения не передаются, настроить метод просто надо наверное
        Page<MessageDto> page = messageService.messageListForUser(pageable, currentUser, author);
        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("subscribersCount", author.getSubscribers().size());
//        определяем является ли пользователь который пришел на чужую страницу его подписчиком
        model.addAttribute("isSubscriber", author.getSubscribers().contains(currentUser));
//        System.out.println("messages = " + messages + "  user = " + user + "  current = " + currentUser);
        model.addAttribute("page", page);
        model.addAttribute("message", message);
// .equals в юзере должен быть переопределен (@Data делает сама)   тут определяем поля  <#if isCurrentUser??> в userMessages,  equals возвращает boolean
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-messages/" + author.getId());

        return "userMessages";
    }

    //    @PathVariable Long user - id вернет...   тут собщение меняем и сохраняем
    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam(value = "file", required = false) MultipartFile file

    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }
            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);

            }
            saveFile(message, file);
            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;

    }
    @GetMapping("/messages/{message}/like")
    public String like(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Message message,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ) {
        Set<User> likes = message.getLikes();

        if (likes.contains(currentUser)) {
            likes.remove(currentUser);
        } else {
            likes.add(currentUser);
        }

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams()
                .entrySet()
                .forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }
}


//    @GetMapping("/main")
//    public String main(
//            @RequestParam(required = false, defaultValue = "") String filter,
//            Model model,
//            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
//    ) {
//
//        Page<Message> page;
//
//        if (filter != null && !filter.isEmpty()) {
//            page = messageRepo.findByTag(filter, pageable);
//        } else {
//            page = messageRepo.findAll(pageable);
//        }
//
//        model.addAttribute("page", page);
//        model.addAttribute("url", "/main");
//        model.addAttribute("filter", filter);
//
//        return "main";
//    }

//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @Valid Message message,
//            BindingResult bindingResult,
//            Model model,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//        message.setAuthor(user);
//
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            model.mergeAttributes(errorsMap);
//            model.addAttribute("message", message);
//        } else {
//            if (file != null && !file.getOriginalFilename().isEmpty()) {
//                File uploadDir = new File(uploadPath);
//
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//
//                String uuidFile = UUID.randomUUID().toString();
//                String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//                file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//                message.setFilename(resultFilename);
//            }
//
//            model.addAttribute("message", null);
//
//            messageRepo.save(message);
//        }
//
//        Iterable<Message> messages = messageRepo.findAll();
//
//        model.addAttribute("messages", messages);
//
//        return "main";
//    }


//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @Valid Message message,
////     BindingResult bindingResult хранит список аргументов и значения ошибок валидации, Всегда идет только ПЕРЕД Model model,
//            BindingResult bindingResult,
//            Model model,
//            @RequestParam("file") MultipartFile file) throws IOException {
//        //взяли из введенной в форму текст и тег, сохранили в БД
//        message.setAuthor(user);
////  если у BindingResult ошибки то не пойдет дальше метод
//        if (bindingResult.hasErrors()) {
//
//            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
////            положили ошибки в модель
//            model.mergeAttributes(errorMap);
//            model.addAttribute("message", message);
//
//        } else {
//            if (file != null && !file.getOriginalFilename().isEmpty()) {
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) {
////            Обезопасим проверкой есть ли папка для загрузки файлов
//                    uploadDir.mkdir();
//                }
////     Здесь страхуемся от коллизии UUID = universe unic id, каждый файл будет отличным, хотя лучше бы отдельное репо под картинки
//                String uuidFile = UUID.randomUUID().toString();
//                System.out.println("uuidFile " + uuidFile);
//                String resultFileName = uuidFile + ".jpg" + file.getOriginalFilename();
//                System.out.println("resultFileName " + resultFileName);
//                file.transferTo(new File(uploadPath + "/" + resultFileName));  //загружаем файл
//                message.setFilename(resultFileName);
//            }
////        Message message = new Message(text, tag);
//            messageRepo.save(message);
//        }
////засунули в форму результат, т.е. в списке всех сообщений добавиться новое введенное
//        Iterable<Message> messages = messageRepo.findAll();
//        model.addAttribute("messages", messages);
//        return "redirect:/main";
//    }


//    @PostMapping("filter")
//    public String filter(@RequestParam String filter, Map<String, Object> model) {
//
//        List<Message> messages = messageRepo.findByTag(filter);
//
//        if (messages.equals(null) || messages.isEmpty()) {
//            Iterable<Message> allMessages = messageRepo.findAll();
//            model.put("messages", allMessages);
//            return "main";
//        }
//        //Соответственно положит в модель в список сообщений только сообщения с одним тегом. Но тут с пути фильтра нет
//        model.put("messages", messages);
//        return "main";
//    }



