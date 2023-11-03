package com.rv.society.domain.util;

import com.rv.society.domain.User;

public abstract class MessageHelper {

    public static String getAuthorName(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
