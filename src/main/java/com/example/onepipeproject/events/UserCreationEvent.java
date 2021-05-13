package com.example.onepipeproject.events;

import com.example.onepipeproject.model.User;
import org.springframework.context.ApplicationEvent;

public class UserCreationEvent extends ApplicationEvent {
    private User user;

    public UserCreationEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}