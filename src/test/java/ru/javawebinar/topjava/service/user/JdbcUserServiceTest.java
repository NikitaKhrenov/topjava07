package ru.javawebinar.topjava.service.user;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by Nikita on 04.07.2016.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JDBC})
public class JdbcUserServiceTest extends UserServiceTest {
}
