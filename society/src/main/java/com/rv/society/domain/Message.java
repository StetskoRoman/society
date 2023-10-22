package com.rv.society.domain;

import jakarta.persistence.*;
import lombok.*;



@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String tag;

    //много сообщений у одного автора может быть, подгружаются сразу все сообщения одного автора, к таблице сообщений прибавили колонку c названием user_id (по умолчанию было бы autor_id)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
//храним только имя файла т.к. путь указан в проперти
    private String filename;

    //Этот метод передаваться будет в шаблон, т.к. не у всех сообщений могут быть авторы
    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }


        public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }


}
