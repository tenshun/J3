package com.tenshun.web;

import com.tenshun.repository.UserRepository;
import groovy.lang.Grab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class WelcomeController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String base(Model model) {
        return "redirect:welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeGet(Principal principal, Model model) {

        model.addAttribute("name", principal.getName());
        return "welcome";
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String welcomePost() {
        return "welcome";
    }


    @GetMapping(value = "/users")
    public String getUserList(Model model){
        model.addAttribute("userlist", userRepository.findAll());
        return "users";
    }


}
