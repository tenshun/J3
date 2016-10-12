package com.tenshun.model.dto;

import com.tenshun.model.entity.Role;
import com.tenshun.model.entity.User;
import com.tenshun.utils.Constants;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private Set<String> authorities;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), user.getEmail(), user.getAuthorities().stream().map(Role::getName)
                        .collect(Collectors.toSet()));
    }

    public UserDTO(String login, String email, Set<String> authorities) {
        this.login = login;
        this.email = email;
        this.authorities = authorities;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }


    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", authorities=" + authorities +
                "}";
    }
}
