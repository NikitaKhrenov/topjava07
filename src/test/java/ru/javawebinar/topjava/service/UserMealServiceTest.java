package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by Nikita on 22.06.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    private static final Comparator<UserMeal> USER_MEAL_COMPARATOR = Comparator.comparing(UserMeal::getDateTime).reversed();

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        UserMeal meal = service.get(START_SEQ + 2, USER_ID);
        MATCHER.assertEquals(MEAL1, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(START_SEQ + 8, ADMIN_ID);
        service.delete(START_SEQ + 9, ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(MEAL9), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1, USER_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        Collection<UserMeal> filtered = service.getBetweenDateTimes(LocalDateTime.parse("2016-06-21T08:00"), LocalDateTime.parse("2016-06-21T15:00"), ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL7, MEAL8).stream()
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList()), filtered);
    }

    @Test
    public void getAll() throws Exception {
        Collection<UserMeal> all = service.getAll(ADMIN_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL7, MEAL8, MEAL9).stream()
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList()), all);
    }

    @Test
    public void update() throws Exception {
        UserMeal updated = new UserMeal(MEAL1.getId(), MEAL1.getDateTime(), MEAL1.getDescription(), MEAL1.getCalories());
        updated.setCalories(330);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(START_SEQ + 2, USER_ID));
    }

    @Test
    public void save() throws Exception {
        UserMeal newMeal = new UserMeal(null, LocalDateTime.parse("2016-06-22T10:00"), "Завтрак", 100);
        UserMeal created = service.save(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL7, MEAL8, MEAL9, newMeal).stream()
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList()), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteAnothers() throws Exception {
        service.delete(START_SEQ + 2, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getAnothers() throws Exception {
        service.get(START_SEQ + 2, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateAnothers() throws Exception {
        UserMeal updated = new UserMeal(MEAL1.getId(), MEAL1.getDateTime(), MEAL1.getDescription(), MEAL1.getCalories());
        updated.setCalories(330);
        service.update(updated, ADMIN_ID);
    }

}