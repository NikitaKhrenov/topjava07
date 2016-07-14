package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;

/**
 * Created by Nikita on 11.07.2016.
 */
public abstract class AbstractUserServiceTestWithHibernateCache extends AbstractUserServiceTest {

    @Autowired
    protected JpaUtil jpaUtil;

    @Autowired
    protected UserService service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
