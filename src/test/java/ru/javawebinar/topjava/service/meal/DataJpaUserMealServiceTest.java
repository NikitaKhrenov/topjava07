package ru.javawebinar.topjava.service.meal;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by Nikita on 04.07.2016.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest {

    @Test
    public void testGetWithUser() throws Exception {
        UserMeal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        actual.setUser(UserTestData.ADMIN);
        MATCHER.assertEquals(ADMIN_MEAL, actual);
    }

    @Test
    public void testGetWithUserNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.getWithUser(MEAL1_ID, ADMIN_ID);
    }
}
