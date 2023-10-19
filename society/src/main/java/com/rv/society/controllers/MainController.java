package com.rv.society.controllers;

import com.rv.society.domain.Message;
import com.rv.society.domain.User;
import com.rv.society.repos.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MessageRepo messageRepo;

//   @Value("${upload.path}") говорим что хотим получить переменную из контекста в пропертис, т. е.  uploadPath будет upload.path=/Program/Projects/MyProjects/Twitter imitation/society/src/main/resources/static/files
    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepo.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }



    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file) throws IOException {
        //взяли из введенной в форму текст и тег, сохранили в БД
        Message message = new Message(text, tag, user);

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
//                Обезопасим проверкой есть ли папка для загрузки файлов
                uploadDir.mkdir();
            }
//            Здесь страхуемся от коллизии UUID = universe unic id, каждый файл будет отличным, хотя лучше бы отдельное репо под картинки
            String uuidFile = UUID.randomUUID().toString();
            System.out.println("uuidFile "+uuidFile);
            String resultFileName = uuidFile + ".jpg" + file.getOriginalFilename();
            System.out.println("resultFileName " +resultFileName);
            file.transferTo(new File(uploadPath + "/" + resultFileName));  //загружаем файл
            message.setFilename(resultFileName);
        }
//        Message message = new Message(text, tag);
        messageRepo.save(message);
//засунули в форму результат, т.е. в списке всех сообщений добавиться новое введенное
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "redirect:/main";
    }

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


}
