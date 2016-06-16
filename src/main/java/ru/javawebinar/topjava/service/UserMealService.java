package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    List<UserMealWithExceed> getAll(int userId) throws NotFoundException;

    List<UserMealWithExceed> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId);

    UserMeal get(int id, int userId) throws NotFoundException;

    UserMeal save(UserMeal usermeal, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    void update(UserMeal userMeal, int userId) throws NotFoundException;
}
