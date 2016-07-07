package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Nikita on 04.07.2016.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class JpaUserMealServiceTest extends UserMealServiceTest {
}
