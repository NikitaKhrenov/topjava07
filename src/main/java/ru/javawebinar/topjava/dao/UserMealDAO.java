package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by Nikita on 14.06.2016.
 */
public interface UserMealDAO {
    UserMeal get(int id);
    void create(UserMeal userMeal);
    void update(UserMeal userMeal);
    void delete(int id);
    List<UserMeal> listUserMeal();
}
