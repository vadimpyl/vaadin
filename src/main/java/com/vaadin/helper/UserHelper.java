package com.vaadin.helper;

import com.vaadin.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 2018-07-11.
 */
public class UserHelper
{
    static List<User> userList = new ArrayList<>();

    public static List<User> listOfUsers()
    {
        userList.add(new User("a","John","a"));
        userList.add(new User("b","John","b"));
        return userList;
    }

    public static void addToList(final User user)
    {
        userList.add(user);
    }
}
