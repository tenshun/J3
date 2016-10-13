package com.tenshun.web;

import groovy.lang.Grab;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.TimeZone;

@Controller
public class WelcomeController {

    @Grab("org.webjars:bootstrap:3.3.5")


    @RequestMapping("/")
    public String base(Model model) {
        return "redirect:welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeGet(Principal principal, Model model, TimeZone timeZone) {
        model.addAttribute("timezone", timeZone.getID());
        model.addAttribute("name", principal.getName());
        return "welcome";
    }

    @RequestMapping(value = "/welcome2", method = RequestMethod.GET)
    public String welcomeGetWithNoPrincipal(Model model) {
        model.addAttribute("name", "test");
        return "welcome";
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String welcomePost() {
        return "welcome";
    }


}
