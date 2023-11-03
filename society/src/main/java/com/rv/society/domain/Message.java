package com.rv.society.domain;

import com.rv.society.domain.dto.MessageDto;
import com.rv.society.domain.util.MessageHelper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@ToString(of = {"id", "text", "tag"})
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
public class Message  {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank(message = "fill the message")
    @Size(max = 2048, message = "limit of signs in message")
    private String text;

    @NotBlank
    @NotNull
    @Size(max = 255, message = "limit of signs in tag")
    private String tag;

    //много сообщений у одного автора может быть, подгружаются сразу все сообщения одного автора, к таблице сообщений прибавили колонку c названием user_id (по умолчанию было бы autor_id)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
//храним только имя файла т.к. путь указан в проперти
    private String filename;

    @ManyToMany
    @JoinTable(name = "message_likes",
            joinColumns = {@JoinColumn(name = "message_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> likes = new HashSet<>();

    //Этот метод передаваться будет в шаблон, т.к. не у всех сообщений могут быть авторы
    public String getAuthorName() {
        return MessageHelper.getAuthorName(author);
    }


        public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }


}
