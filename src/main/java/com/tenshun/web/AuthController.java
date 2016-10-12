package com.tenshun.web;


import com.tenshun.model.dto.UserDTO;
import com.tenshun.repository.UserRepository;
import com.tenshun.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /*@RequestMapping("/login")
    public String errorAuth(Model model){
        model.addAttribute("error", true);
        return "error";
    }*/

    /*@RequestMapping(value = "/users/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> auth(@Valid UserDTO userDTO) {

    }*/


}
