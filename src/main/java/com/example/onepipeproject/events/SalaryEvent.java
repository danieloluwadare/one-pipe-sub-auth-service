package com.example.onepipeproject.events;

import com.example.onepipeproject.model.User;
import org.springframework.context.ApplicationEvent;

public class SalaryEvent extends ApplicationEvent {
    private User user;

    public SalaryEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}