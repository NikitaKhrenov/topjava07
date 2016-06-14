package ru.javawebinar.topjava;

import ru.javawebinar.topjava.dao.InMemoryUserMealDAO;
import ru.javawebinar.topjava.dao.UserMealDAO;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        UserMealDAO userMealDAO = new InMemoryUserMealDAO();
        System.out.println(userMealDAO.get(1));
        userMealDAO.create(new UserMeal(LocalDateTime.now(), "Обед", 1500));
        userMealDAO.update(new UserMeal(1, LocalDateTime.now().minusDays(1), "Ужин", 200));
        userMealDAO.delete(3);
        userMealDAO.listUserMeal().forEach(System.out::println);
    }
}
