package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final UserMeal MEAL1 = new UserMeal(START_SEQ + 2, LocalDateTime.parse("2016-06-20T10:00"), "Завтрак", 500);
    public static final UserMeal MEAL2 = new UserMeal(START_SEQ + 3, LocalDateTime.parse("2016-06-20T15:00"), "Обед", 800);
    public static final UserMeal MEAL3 = new UserMeal(START_SEQ + 4, LocalDateTime.parse("2016-06-20T19:00"), "Ужин", 300);
    public static final UserMeal MEAL4 = new UserMeal(START_SEQ + 5, LocalDateTime.parse("2016-06-21T11:00"), "Завтрак", 300);
    public static final UserMeal MEAL5 = new UserMeal(START_SEQ + 6, LocalDateTime.parse("2016-06-21T14:30"), "Обед", 1000);
    public static final UserMeal MEAL6 = new UserMeal(START_SEQ + 7, LocalDateTime.parse("2016-06-21T20:00"), "Ужин", 800);

    public static final UserMeal MEAL7 = new UserMeal(START_SEQ + 8, LocalDateTime.parse("2016-06-21T09:00"), "Завтрак", 500);
    public static final UserMeal MEAL8 = new UserMeal(START_SEQ + 9, LocalDateTime.parse("2016-06-21T14:00"), "Обед", 700);
    public static final UserMeal MEAL9 = new UserMeal(START_SEQ + 10, LocalDateTime.parse("2016-06-21T18:00"), "Ужин", 900);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
