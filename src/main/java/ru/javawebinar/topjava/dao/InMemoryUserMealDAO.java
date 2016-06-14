package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Created by Nikita on 14.06.2016.
 */
public class InMemoryUserMealDAO implements UserMealDAO {

    private ConcurrentMap<Integer, UserMeal> inMemoryUserMealMap = new ConcurrentHashMap<>();
    {
        UserMeal meal1 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        UserMeal meal2 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        UserMeal meal3 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        UserMeal meal4 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        UserMeal meal5 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        UserMeal meal6 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        inMemoryUserMealMap.put(meal1.getId(), meal1);
        inMemoryUserMealMap.put(meal2.getId(), meal2);
        inMemoryUserMealMap.put(meal3.getId(), meal3);
        inMemoryUserMealMap.put(meal4.getId(), meal4);
        inMemoryUserMealMap.put(meal5.getId(), meal5);
        inMemoryUserMealMap.put(meal6.getId(), meal6);
    }

    @Override
    public UserMeal get(int id) {
        return inMemoryUserMealMap.get(id);
    }

    @Override
    public void create(UserMeal userMeal) {
        inMemoryUserMealMap.put(userMeal.getId(), userMeal);
    }

    @Override
    public void update(UserMeal userMeal) {
        inMemoryUserMealMap.merge(userMeal.getId(), userMeal, (userMeal1, userMeal2) -> userMeal2);
    }

    @Override
    public void delete(int id) {
        inMemoryUserMealMap.remove(id);
    }

    @Override
    public List<UserMeal> listUserMeal() {
        return inMemoryUserMealMap.values().stream().collect(Collectors.toList());
    }
}
