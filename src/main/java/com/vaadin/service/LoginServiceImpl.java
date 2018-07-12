package com.vaadin.service;

import com.vaadin.helper.UserHelper;
import com.vaadin.model.User;

public class LoginServiceImpl implements LoginService
{

    @Override
    public User login(final String username, final String password)
    {

        return UserHelper.listOfUsers().stream().
                filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword())).findAny().orElse(null);
    }
}
