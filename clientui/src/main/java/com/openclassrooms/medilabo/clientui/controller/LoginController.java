package com.openclassrooms.medilabo.clientui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * Displays the login page.
     *
     * @return the login page
     */
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

}
