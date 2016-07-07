package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Nikita on 04.07.2016.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Query(name = UserMeal.ALL_SORTED)
    List<UserMeal> findAll(@Param("userId") int userId);

    @Query(name = UserMeal.GET_BETWEEN)
    List<UserMeal> findBetween(@Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate,
                               @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(name = UserMeal.DELETE)
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    UserMeal save(UserMeal user);

    @Query(name = UserMeal.GET)
    UserMeal findOne(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT m FROM UserMeal m LEFT JOIN FETCH m.user WHERE m.id=:id AND m.user.id=:userId")
    UserMeal getWithUser(@Param("id") int id, @Param("userId") int userId);
}
