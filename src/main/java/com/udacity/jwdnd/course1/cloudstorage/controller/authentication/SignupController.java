package com.udacity.jwdnd.course1.cloudstorage.controller.authentication;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @GetMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        model.addAttribute("message", "");
        return "signup";
    }

    @PostMapping("/signup")
    public void signupPost(@ModelAttribute User user, Model model) {
        System.out.println("signupPost");
    }
}
