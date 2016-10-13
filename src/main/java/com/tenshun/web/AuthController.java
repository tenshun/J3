package com.tenshun.web;


import com.tenshun.model.dto.UserDTO;
import com.tenshun.model.entity.User;
import com.tenshun.repository.UserRepository;
import com.tenshun.service.user.UserService;
import com.tenshun.web.form.RegForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;

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

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String signUpGet(Model model){
        RegForm form = new RegForm();
        model.addAttribute("regForm", form);
        return "join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("regForm") @Valid RegForm regForm,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            return "join";
        }

        if (userRepository.findOneByLogin(regForm.getLogin().toLowerCase()).isPresent()) {
            model.addAttribute("loginExistError", "Login already exists");
            return "join";
        } else if (userRepository.findOneByEmail(regForm.getEmail()).isPresent()) {
            model.addAttribute("emailExistsError", "Email already in use");
            return "join";
        } else {
            User newUser = userService.createUser(regForm.getLogin(), regForm.getPassword(), regForm.getEmail());

            return "redirect:/welcome";
        }

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
