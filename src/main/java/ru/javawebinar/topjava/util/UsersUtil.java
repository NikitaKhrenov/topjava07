package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nikita on 15.06.2016.
 */
public class UsersUtil {
    public static final List<User> USER_LIST = Arrays.asList(
            new User(1, "userName1", "email1", "password1", Role.ROLE_ADMIN),
            new User(2, "userName2", "email2", "password2", Role.ROLE_USER)
    );
}
