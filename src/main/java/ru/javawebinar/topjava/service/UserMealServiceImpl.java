package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public List<UserMealWithExceed> getAll(int userId) throws NotFoundException {
        return repository.getAll(userId);
    }

    @Override
    public List<UserMealWithExceed> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        return repository.getFiltered(startDate, startTime, endDate, endTime, userId);
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {
        return repository.get(id, userId);
    }

    @Override
    public UserMeal save(UserMeal usermeal, int userId) throws NotFoundException {
        return repository.save(usermeal);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        repository.delete(id);
    }

    @Override
    public void update(UserMeal userMeal, int userId) throws NotFoundException {
        repository.save(userMeal);
    }
}
