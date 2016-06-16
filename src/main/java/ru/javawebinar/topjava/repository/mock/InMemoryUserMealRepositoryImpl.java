package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);

    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public UserMeal get(int id, int userId) throws NotFoundException {

        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<UserMealWithExceed> getAll(int userId) {
        return repository.values().stream()
                .filter(um -> um.getUser().getId().equals(userId))
                .collect(Collectors.toList()).isEmpty() ?
                Collections.emptyList() :
                UserMealsUtil.getWithExceeded(repository.values().stream()
                        .filter(um -> um.getUser().getId().equals(userId))
                        .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                        .collect(Collectors.toList()), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public List<UserMealWithExceed> getFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, int userId) {
        return repository.values().stream()
                .filter(um -> um.getUser().getId().equals(userId))
                .filter(um -> TimeUtil.isBetween(um.getDateTime(), startDate, startTime, endDate, endTime))
                .collect(Collectors.toList()).isEmpty() ?
                Collections.emptyList() :
                UserMealsUtil.getWithExceeded(repository.values().stream()
                        .filter(um -> um.getUser().getId().equals(userId))
                        .filter(um -> TimeUtil.isBetween(um.getDateTime(), startDate, startTime, endDate, endTime))
                        .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                        .collect(Collectors.toList()), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
}

