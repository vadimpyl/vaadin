package com.vaadin.service;

import com.vaadin.model.User;

/**
 * Created by Vadim on 2018-07-10.
 */
public interface LoginService
{
    public User login(final String username, final String password);
}
