package com.example.onepipeproject.listeners;

import com.example.onepipeproject.events.SalaryEvent;
import com.example.onepipeproject.events.UserCreationEvent;
import com.example.onepipeproject.service.SalaryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

public class UserListener {

    private SalaryHistoryService salaryHistoryService;

    @Autowired
    public UserListener(SalaryHistoryService salaryHistoryService) {
        this.salaryHistoryService = salaryHistoryService;
    }

    @EventListener(SalaryEvent.class)
    public void reportUserCreation(SalaryEvent event) {
        salaryHistoryService.create(event.getUser().getId(),event.getUser().getSalary());
        System.out.println("Increment counter as new user was created: " + event);
    }

    @EventListener(UserCreationEvent.class)
    public void syncUserToExternalSystem(UserCreationEvent event) {
        // e.g. send a message to a messaging queue to inform other systems
        salaryHistoryService.create(event.getUser().getId(),event.getUser().getSalary());
        System.out.println("informing other systems about new user: " + event);
    }
}
