package com.example.onepipeproject.listeners;

import com.example.onepipeproject.events.SalaryEvent;
import com.example.onepipeproject.events.UserCreationEvent;
import org.springframework.context.event.EventListener;

public class UserListener {
    @EventListener(SalaryEvent.class)
    public void reportUserCreation(SalaryEvent event) {
        // e.g. increment a counter to report the total amount of new users
        System.out.println("Increment counter as new user was created: " + event);
    }

    @EventListener(UserCreationEvent.class)
    public void syncUserToExternalSystem(UserCreationEvent event) {
        // e.g. send a message to a messaging queue to inform other systems
        System.out.println("informing other systems about new user: " + event);
    }
}
