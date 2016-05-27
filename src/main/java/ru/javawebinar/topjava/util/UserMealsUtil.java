package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

        for (UserMealWithExceed meal : getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000)) {
            System.out.println(meal.toString());
        }
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> filteredMealList = new ArrayList<>();

        // Ключ - день, значение - суммарное колличество каллорий за этот день
        Map<LocalDate, Integer> caloriesMap = new HashMap<>();
        for (UserMeal meal : mealList) {
            int calories = meal.getCalories();
            int currentCalories = 0;
            LocalDate currentDate = meal.getDateTime().toLocalDate();

            // Если в карте уже есть данные за этот день, то читаем это значение
            if (caloriesMap.containsKey(currentDate))
                currentCalories = caloriesMap.get(currentDate);

            // Записываем в карту новое значение
            caloriesMap.put(currentDate, calories + currentCalories);
        }
        filteredMealList.addAll(mealList.stream().filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)).map(meal -> new UserMealWithExceed(meal.getDateTime(),
                meal.getDescription(),
                meal.getCalories(),
                caloriesMap.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)).collect(Collectors.toList()));
        return filteredMealList;
    }
}
