package com.tenshun.web.form;


import com.tenshun.web.form.validation.PasswordMatches;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@PasswordMatches
public class RegForm { //TODO add to /resources message_en/message_ru.properties

    @NotNull
    @NotEmpty
    @Size(min = 4)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$") //for alphanumeric
    private String login;


    /**
     * Minimum 8 characters at least 1 Uppercase Alphabet, 1 Lowercase Alphabet and 1 Number:

     * expression: "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"
     * https://stackoverflow.com/questions/19605150/regex-for-password-must-be-contain-at-least-8-characters-least-1-number-and-bot
     */

    @NotNull
    @NotEmpty
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
