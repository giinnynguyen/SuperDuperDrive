package com.udacity.jwdnd.course1.cloudstorage.controller.authentication;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(User user, Model model) {
        model.addAttribute("message", "");
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(User user, Model model) {

        return "login";
    }
}
