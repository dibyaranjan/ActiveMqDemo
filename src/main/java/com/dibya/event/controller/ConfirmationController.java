package com.dibya.event.controller;

import com.dibya.event.service.ConfirmationPageLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfirmationController {
    @Autowired
    private ConfirmationPageLoader confirmationPageLoader;

    @RequestMapping("/confirmation")
    public String loadConfirmationPage(String message) {
        confirmationPageLoader.setMessage(message);
        confirmationPageLoader.sendConfimationPageLoadNotification();

        System.out.println("sf");

        return "done";
    }
}
