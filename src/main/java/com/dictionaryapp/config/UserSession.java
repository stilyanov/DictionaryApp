package com.dictionaryapp.config;

import com.dictionaryapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private Long userId;

    private String userName;

    public void login(User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
    }

    public boolean isUserLoggedIn() {
        return userId != 0;
    }

    public void logout() {
        userId = null;
        userName = "";
    }

    public String username() {
        return userName;
    }
}