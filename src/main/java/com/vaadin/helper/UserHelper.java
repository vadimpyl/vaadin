package com.vaadin.helper;

import com.vaadin.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadim on 2018-07-11.
 */
public class UserHelper
{
    static List<User> userList = new ArrayList<User>() {{
        add(new User("user1","John","Bee", "address", "a"));
        add(new User("user2","Lui","Duo", "address", "a"));
        add(new User("user2","Andrew","Hook", "address", "a"));
        add(new User("user2","Anna","Clark", "address", "a"));
        add(new User("user2","Dee","Knock", "address", "a"));
        add(new User("user2","Foo","Lant", "address", "a"));
        add(new User("user2","Ke","Larson", "address", "a"));
        add(new User("user2","Richard","Ant", "address", "a"));
        add(new User("user2","Angelina","Goulton", "address", "a"));
        add(new User("user2","Stephan","Took", "address", "a"));
    }};
    public static List<User> listOfUsers()
    {
        return userList;
    }

    public static void addToList(final User user)
    {
        userList.add(user);
    }
}
