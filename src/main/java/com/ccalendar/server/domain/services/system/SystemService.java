package com.ccalendar.server.domain.services.system;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SystemService {
    private static final String CRON = "0 0 6 * * *";

    @Scheduled(cron = CRON)
    public void loadEvents(){
        System.out.println("Test string from Task!");
    }
}
