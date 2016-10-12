package com.tenshun.web;

import com.tenshun.model.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {


    @RequestMapping(value = "/new-user", method = RequestMethod.POST)
    public String createNewUser(@Validated UserDTO userDTO){
        return ""; //TODO
    }
}
