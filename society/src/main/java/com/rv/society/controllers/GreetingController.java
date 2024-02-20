package com.rv.society.controllers;

import com.rv.society.domain.Message;
import com.rv.society.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {


    private MessageRepo messageRepo;

    public GreetingController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name,
                           Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {

        //нашли все сообщения из репозитория
        Iterable<Message> messages = messageRepo.findAll();

        //положили их в модель, которую передаем в методе и отобразим на странице, "messages" совпадает с{{#messages}}
        model.put("messages", messages);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        //взяли из введенной в форму текст и тег, сохранили в БД
        Message message = new Message(text, tag);
        messageRepo.save(message);
//засунули в форму результат, т.е. в списке всех сообщений добавиться новое введенное
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        List<Message> messages = messageRepo.findByTag(filter);

        if (messages.equals(null) || messages.isEmpty()) {
            Iterable<Message> allMessages = messageRepo.findAll();
            model.put("messages", allMessages);
            return "main";
        }
        //Соответственно положит в модель в список сообщений только сообщения с одним тегом. Но тут с пути фильтра нет
        model.put("messages", messages);
        return "main";
    }


}
