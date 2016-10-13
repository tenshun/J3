package com.tenshun.service.user;

import com.tenshun.model.entity.User;

public interface UserService {

    User createUser(String login, String password, String email);



}
